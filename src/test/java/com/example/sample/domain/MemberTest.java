package com.example.sample.domain;

import com.example.sample.users.domain.Members;
import com.example.sample.users.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 휴대폰번호 길이 체크")
    public void MemberMobileNumberLengthTest() {
        //given
        Optional<Members> members = memberRepository.findById(1L);

        System.out.println("members.get().getUserName() = " + members.get().getUserName());

        //when

        //then
    }
}
