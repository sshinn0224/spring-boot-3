package com.example.sample.users.presentation.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCommandDto {

    private String userName;

    private String mobileNumber;

    private String password;

    private String email;
}
