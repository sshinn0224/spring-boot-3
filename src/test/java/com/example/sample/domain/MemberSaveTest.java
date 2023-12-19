package com.example.sample.domain;

import com.example.sample.users.presentation.command.dto.MemberCommandDto;
import com.example.sample.users.presentation.query.dto.MemberResponse;
import com.example.sample.users.service.MemberFindService;
import com.example.sample.users.service.MemberOperationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberSaveTest {

    @Autowired
    MemberOperationService memberOperationService;

    @Autowired
    MemberFindService memberFindService;

    @Test
    @DisplayName("회원 저장 테스트")
    void MemberSaveOneTest() {
        MemberCommandDto member = MemberCommandDto
                .builder()
                .userName("SHINJAEHO")
                .mobileNumber("11122223333")
                .build();


        memberOperationService.save(member);
        Optional<MemberResponse> memberResponse = memberFindService
                .findAllMember()
                .stream()
                .filter(item -> "SHINJAEHO".equals(item.getUserName()))
                .findFirst();

        assertEquals(member.getUserName(), memberResponse.get().getUserName());
    }

    @Test
    @DisplayName("회원 저장 예외 테스트")
    void MemberSaveExceptionTest() {
        MemberCommandDto member = MemberCommandDto
                .builder()
                .userName("SHINJAEHO")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> memberOperationService.save(member));
        assertEquals(exception.getMessage(), "mobileNumber cannot be null");
    }


}
