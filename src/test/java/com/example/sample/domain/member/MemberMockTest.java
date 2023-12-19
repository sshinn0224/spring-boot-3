package com.example.sample.domain.member;


import com.example.sample.users.presentation.command.SaveMember;
import com.example.sample.users.presentation.command.dto.MemberRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Member;

@WebMvcTest(SaveMember.class)
public class MemberMockTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void validationTest() {
        MemberRegistrationRequest request = MemberRegistrationRequest.builder()
                .userName("SHINJAEHO")
                .mobileNumber("1112222333330")
                .email("test@gmail.com")
                .password("asdf")
                .build();
    }
}
