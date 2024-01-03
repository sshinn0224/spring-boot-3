package com.example.sample.members.service;

import com.example.sample.common.types.AuthTypes;
import com.example.sample.members.domain.Authority;
import com.example.sample.members.domain.Member;
import com.example.sample.members.domain.RefreshToken;
import com.example.sample.members.presentation.command.dto.signUpDto;
import com.example.sample.members.repository.MemberRepository;
import com.example.sample.members.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberOperationService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void save(signUpDto dto) {
        verifyMemberRequestDto(dto);


        Member member = new Member(
                dto.getUsername(),
                dto.getMobileNumber(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getEmail(),
                dto.getGender(),
                new ArrayList<>()
        );

        Authority basicAuthority = new Authority(dto.getUsername(), AuthTypes.BASIC, member);
        member.getAuthorityList().add(basicAuthority);

        Authority adminAuthority = new Authority(dto.getUsername(), AuthTypes.ADMIN, member);
        member.getAuthorityList().add(adminAuthority);

        memberRepository.save(member);
    }



    private void verifyMemberRequestDto(signUpDto dto) {
        // 중복 체크
        memberRepository.findByUserName(dto.getUsername())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("username이 중복 됩니다.");
                }
        );

        if(dto.getUsername() == null) {
            throw new IllegalArgumentException("userName cannot be null");
        }

        if(dto.getMobileNumber() == null) {
            throw new IllegalArgumentException("mobileNumber cannot be null");
        }
    }

    @Transactional
    public void saveRefreshToken(String username, String refreshToken) {
        // 기존 리프레시 토큰이 있다면, 삭제 후 저장 한다.
        refreshTokenRepository.findByUsername(username)
                .ifPresentOrElse(
                        item -> {
                            item.changeRefreshToken(refreshToken);
                        },
                        () -> refreshTokenRepository.save(new RefreshToken(username, refreshToken))
                );
    }
}
