package com.example.sample.users.presentation.command.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class MemberRegistrationResponse {

    private String accessToken;

    private String refreshToken;
}
