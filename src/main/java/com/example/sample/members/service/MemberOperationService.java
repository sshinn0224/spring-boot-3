package com.example.sample.members.service;

import com.example.sample.common.types.AuthTypes;
import com.example.sample.common.utils.Aes128Util;
import com.example.sample.common.utils.Sha256Util;
import com.example.sample.members.domain.Members;
import com.example.sample.members.presentation.command.dto.MemberRegistrationRequest;
import com.example.sample.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberOperationService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public void save(MemberRegistrationRequest dto) {
        verifyMember(dto);

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

    private void verifyMember(MemberRegistrationRequest dto) {
        if(dto.getUsername() == null) {
            throw new IllegalArgumentException("userName cannot be null");
        }

        if(dto.getMobileNumber() == null) {
            throw new IllegalArgumentException("mobileNumber cannot be null");
        }
    }
}
