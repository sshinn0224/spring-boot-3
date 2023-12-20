package com.example.sample.users.presentation.command.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
