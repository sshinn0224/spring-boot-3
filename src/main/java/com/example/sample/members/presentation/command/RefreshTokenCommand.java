package com.example.sample.members.presentation.command;

import com.example.sample.members.jwt.TokenProvider;
import com.example.sample.members.presentation.command.dto.AccessTokenDto;
import com.example.sample.members.presentation.command.dto.RefreshTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RefreshTokenCommand {

    private final TokenProvider tokenProvider;

    @PostMapping("/recreation-access-token")
    public ResponseEntity<AccessTokenDto> validateRefreshToken(@RequestBody RefreshTokenDto dto) {
        /**
         * refreshToken이 만료 됨 -> 로그인
         * refreshToken유효기간이 남아있다 -> accessToken 생성
         */
        String accessToken = tokenProvider.validateRefreshToken(dto.getRefreshToken());

        if(accessToken == null) {
            // refreshToken 만료로 판단
            return new ResponseEntity<>(
                    new AccessTokenDto(),
                    HttpStatus.UNAUTHORIZED
            );
        }

        return new ResponseEntity<>(
                new AccessTokenDto(accessToken),
                HttpStatus.OK
        );
    }
}
