package com.example.sample.domain.member;


import com.example.sample.members.presentation.command.dto.signUpDto;
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
public class MemberPresentationMockTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("username 없다면 500에러가 발생 한다.")
    void usernameValidation() throws Exception {
        signUpDto request = signUpDto.builder()
                .mobileNumber("01022223333")
                .password("1234")
                .email("test@email.com")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    @DisplayName("이상한 Email이 들어오면 500에러가 발생 한다.")
    void emailValidation() throws Exception {
        signUpDto request = signUpDto.builder()
                .username("SHINJAEHO")
                .mobileNumber("01012345678")
                .email("testgmail.com")
                .password("1234")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    @DisplayName("모바일번호가 11자리 이상일 경우 500에러가 발생 한다.")
    void mobileNumberValidation() throws Exception {
        signUpDto request = signUpDto.builder()
                .username("SHINJAEHO@gmail.com")
                .mobileNumber("010123456781")
                .email("test@gmail.com")
                .password("1234")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
