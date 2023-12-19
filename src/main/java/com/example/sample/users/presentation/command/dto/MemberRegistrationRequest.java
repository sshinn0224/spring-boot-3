package com.example.sample.users.presentation.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRegistrationRequest {

    @NotNull
    @Size(max = 20)
    private String userName;

    @NotNull
    @Size(max = 11)
    private String mobileNumber;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;
}
