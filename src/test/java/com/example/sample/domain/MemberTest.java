package com.example.sample.domain;

import com.example.sample.presentation.user.query.view.MemberResponse;
import com.example.sample.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void ReadOneUserTest() {
        //given
        Optional<Members> members = memberRepository.findById(1L);

        System.out.println("members.get().getUserName() = " + members.get().getUserName());

        //when

        //then
    }
}
