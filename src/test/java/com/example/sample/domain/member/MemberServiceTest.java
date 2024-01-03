package com.example.sample.domain.member;

import com.example.sample.members.domain.RefreshToken;
import com.example.sample.members.presentation.command.dto.signUpDto;
import com.example.sample.members.presentation.query.dto.MemberResponse;
import com.example.sample.members.repository.RefreshTokenRepository;
import com.example.sample.members.service.MemberFindService;
import com.example.sample.members.service.MemberCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberCommandService memberCommandService;

    @Autowired
    MemberFindService memberFindService;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Test
    @DisplayName("회원 저장 테스트")
    @Transactional
    void MemberSaveOneTest() {
        signUpDto member = signUpDto
                .builder()
                .username("SHINJAEHO@gmail.com")
                .password("1234")
                .mobileNumber("11122223333")
                .build();


        memberCommandService.save(member);
        MemberResponse memberResponse = memberFindService
                .findAllMember()
                .stream()
                .filter(item -> "SHINJAEHO@gmail.com".equals(item.getUserName()))
                .findFirst().orElse(new MemberResponse());

        assertEquals(member.getUsername(), memberResponse.getUserName());
    }

    @Test
    @DisplayName("회원 저장 예외 테스트")
    @Transactional
    void MemberSaveExceptionTest() {
        signUpDto member = signUpDto
                .builder()
                .username("SHINJAEHO")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> memberCommandService.save(member));
        assertEquals(exception.getMessage(), "mobileNumber cannot be null");
    }

    @Test
    @DisplayName("회원 중복 저장 에러 테스트")
    @Transactional
    void SameMemberExceptionTest() {
        signUpDto member = signUpDto
                .builder()
                .username("SHINJAEHO@gmail.com")
                .password("1234")
                .mobileNumber("11122223333")
                .build();


        memberCommandService.save(member);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> memberCommandService.save(member));
        assertEquals("username이 중복 됩니다.", exception.getMessage());
    }

    @Test
    @DisplayName("RefreshToken 저장 테스트")
    @Transactional
    void saveRefreshTokenTest() {

        memberCommandService.saveRefreshToken("TEST", "askdkdkTEST");

        RefreshToken refreshToken = refreshTokenRepository.findByUsername("TEST").get();

        assertEquals("askdkdkTEST", refreshToken.getRefreshToken());
    }

    @Test
    @DisplayName("RefreshToken 중복일 때 테스트")
    @Transactional
    void saveRefreshTokenDoubleTest() {
        memberCommandService.saveRefreshToken("TEST", "askdkdkTEST");
        memberCommandService.saveRefreshToken("TEST", "sampleKey");

        RefreshToken refreshToken = refreshTokenRepository.findByUsername("TEST").get();

        assertEquals("sampleKey" , refreshToken.getRefreshToken());
    }


}
