package com.example.sample.presentation.member.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCommandDto {

    private String userName;

    private String mobileNumber;
}
