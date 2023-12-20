package com.example.sample.members.service;

import com.example.sample.common.utils.Aes128Util;
import com.example.sample.members.domain.Members;
import com.example.sample.members.presentation.command.dto.MemberRegistrationRequest;
import com.example.sample.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberOperationService {

    private final MemberRepository memberRepository;

    private final Aes128Util aes128Util;

    public void save(MemberRegistrationRequest dto) {
        verifyMember(dto);

        Members member = new Members(
                aes128Util.aes128Encrypted(dto.getUsername()),
                aes128Util.aes128Encrypted(dto.getMobileNumber()),
                dto.getPassword(),
                dto.getEmail()
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
