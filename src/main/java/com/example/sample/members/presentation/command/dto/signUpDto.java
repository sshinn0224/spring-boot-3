package com.example.sample.members.presentation.command.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class signUpDto {

    @NotBlank
    @Size(max = 20, message = "이름은 20자 보다 작아야 합니다.")
    private String username;

    @NotBlank
    @Size(max = 11, message = "휴대폰 번호는 11자 보다 작아야 합니다.")
    private String mobileNumber;

    @NotBlank
    private String password;

    @NotBlank
    @Email(message = "잘못된 email 입니다.")
    private String email;

    private String gender;
}
