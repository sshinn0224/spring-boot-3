package com.example.sample.members.jwt;

import com.example.sample.members.presentation.command.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.access-token-validity-in-seconds}") long tokenValidityInSeconds,
                         @Value("${jwt.refresh-token-validity-in-seconeds}") long refreshTokenValidityInSeconds
    ) {
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = tokenValidityInSeconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date accessTokenValidity = new Date(now + this.accessTokenValidityInMilliseconds);
        Date refreshTokenValidity = new Date(now + this.refreshTokenValidityInMilliseconds);

        return new TokenDto(
                Jwts
                        .builder()
                        .setSubject(authentication.getName())
                        .claim(AUTHORITIES_KEY, authorities)
                        .signWith(key, SignatureAlgorithm.HS512)
                        .setExpiration(accessTokenValidity)
                        .compact()
                ,
                Jwts
                        .builder()
                        .setSubject(authentication.getName())
                        .claim(AUTHORITIES_KEY, authorities)
                        .signWith(key, SignatureAlgorithm.HS512)
                        .setExpiration(refreshTokenValidity)
                        .compact()
        );
    }

    private String recreationAccessToken(String username, Object authorities) {

        long now = (new Date()).getTime();
        Date refreshTokenValidity = new Date(now + this.refreshTokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(refreshTokenValidity)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        /**
         * jwt 토큰을 사용 하여 사용자의 권한 정보를 반환 한다.
         */
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰 입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원 하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못 되었습니다.");
        }
        return false;
    }

    public String validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            // refreshToken 이 유효한 경우, accessToken을 다시 만들어서 반환 한다.
            if(!claims.getBody().getExpiration().before(new Date())) {
                return recreationAccessToken(
                        claims.getBody().get("sub").toString(),
                        claims.getBody().get("auth")
                );
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
