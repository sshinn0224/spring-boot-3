package com.example.sample.members.presentation.command;


import com.example.sample.members.jwt.JwtFilter;
import com.example.sample.members.jwt.TokenProvider;
import com.example.sample.members.presentation.command.dto.LoginRequest;
import com.example.sample.members.presentation.command.dto.TokenDto;
import com.example.sample.members.service.MemberCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthCommand {

    private static final Logger logger = LoggerFactory.getLogger(AuthCommand.class);

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    private final MemberCommandService memberCommandService;

    /**
     * 로그인
     * 토큰이 만료 된 경우 토큰을 재 발급 해준다.
     * @param loginDto
     * @return
     */
    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authCommand(@RequestBody @Valid LoginRequest loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /**
         * id, password 검증 통과 후
         * refreshToken, accessToken을 생성 한다.
         */
        TokenDto tokenDto = tokenProvider.createToken(authentication);

        // refreshToken은 저장 한다.
        memberCommandService.saveRefreshToken(loginDto.getUsername(), tokenDto.getRefreshToken());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getAccessToken());

        return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
    }

}
