package com.example.sample.domain.member;

import com.example.sample.members.presentation.command.dto.MemberRegistrationRequest;
import com.example.sample.members.presentation.query.dto.MemberResponse;
import com.example.sample.members.service.MemberFindService;
import com.example.sample.members.service.MemberOperationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberOperationService memberOperationService;

    @Autowired
    MemberFindService memberFindService;

    @Test
    @DisplayName("회원 저장 테스트")
    void MemberSaveOneTest() {
        MemberRegistrationRequest member = MemberRegistrationRequest
                .builder()
                .username("SHINJAEHO")
                .password("1234")
                .mobileNumber("11122223333")
                .build();


        memberOperationService.save(member);
        MemberResponse memberResponse = memberFindService
                .findAllMember()
                .stream()
                .filter(item -> "SHINJAEHO".equals(item.getUserName()))
                .findFirst().orElse(new MemberResponse());

        assertEquals(member.getUsername(), memberResponse.getUserName());
    }

    @Test
    @DisplayName("회원 저장 예외 테스트")
    void MemberSaveExceptionTest() {
        MemberRegistrationRequest member = MemberRegistrationRequest
                .builder()
                .username("SHINJAEHO")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> memberOperationService.save(member));
        assertEquals(exception.getMessage(), "mobileNumber cannot be null");
    }


}
