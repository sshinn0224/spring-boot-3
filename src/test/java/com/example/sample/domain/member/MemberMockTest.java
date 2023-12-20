package com.example.sample.domain.member;


import com.example.sample.users.presentation.command.SaveMember;
import com.example.sample.users.presentation.command.dto.MemberRegistrationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.lang.reflect.Member;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberMockTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void validationTest() throws Exception {
        MemberRegistrationRequest request = MemberRegistrationRequest.builder()
                .mobileNumber("1112222333330")
                .email("test@gmail.com")
                .password("asdf")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());




    }
}
