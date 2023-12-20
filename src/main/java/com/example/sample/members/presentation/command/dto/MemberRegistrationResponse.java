package com.example.sample.members.presentation.command.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRegistrationResponse {

    private String accessToken;

    private String refreshToken;
}
