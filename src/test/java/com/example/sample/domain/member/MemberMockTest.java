package com.example.sample.domain.member;


import com.example.sample.users.presentation.command.dto.MemberRegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberMockTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("username 없다면 500에러가 발생 한다.")
    void usernameNullTest() throws Exception {
        MemberRegistrationRequest request = MemberRegistrationRequest.builder()
                .mobileNumber("1112222333330")
                .email("test@gmail.com")
                .password("1234")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    @DisplayName("이상한 Email이 들어오면 500에러가 발생 한다.")
    void validationTest() throws Exception {
        MemberRegistrationRequest request = MemberRegistrationRequest.builder()
                .userName("SHINJAEHO")
                .mobileNumber("1112222333330")
                .email("testgmail.com")
                .password("1234")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/member")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
