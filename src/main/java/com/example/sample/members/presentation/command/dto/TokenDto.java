package com.example.sample.members.presentation.command.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenDto {

    public TokenDto(String token) {
        this.token = token;
    }

    private String token;
}
