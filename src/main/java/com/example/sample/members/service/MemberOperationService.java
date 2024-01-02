package com.example.sample.members.service;

import com.example.sample.common.types.AuthTypes;
import com.example.sample.common.utils.Aes128Util;
import com.example.sample.common.utils.Sha256Util;
import com.example.sample.members.domain.Members;
import com.example.sample.members.domain.RefreshToken;
import com.example.sample.members.presentation.command.dto.MemberRegistrationRequest;
import com.example.sample.members.repository.MemberRepository;
import com.example.sample.members.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberOperationService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void save(MemberRegistrationRequest dto) {
        verifyMemberRequestDto(dto);

        Members member = new Members(
                dto.getUsername(),
                dto.getMobileNumber(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getEmail(),
                dto.getGender(),
                AuthTypes.BASIC // 일반 회원은 BASIC 타입으로 처리 한다.
        );

        memberRepository.save(member);
    }
    private void verifyMemberRequestDto(MemberRegistrationRequest dto) {
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
