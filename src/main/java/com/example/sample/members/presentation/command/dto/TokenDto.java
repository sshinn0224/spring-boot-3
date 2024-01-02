package com.example.sample.members.presentation.command.dto;

import lombok.Data;


public class TokenDto {

    public TokenDto(String token) {
        this.token = token;
    }

    private String token;
}
