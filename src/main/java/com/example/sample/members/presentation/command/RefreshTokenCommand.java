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
        return new ResponseEntity<>(
                new AccessTokenDto(tokenProvider.validateRefreshToken(dto.getRefreshToken())),
                HttpStatus.OK
        );
    }
}
