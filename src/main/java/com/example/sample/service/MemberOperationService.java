package com.example.sample.service;

import com.example.sample.domain.Members;
import com.example.sample.presentation.member.command.dto.MemberCommandDto;
import com.example.sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberOperationService {

    private final MemberRepository memberRepository;

    public void save(MemberCommandDto dto) {
        verifyMember(dto);

        Members member = new Members(
                dto.getUserName(),
                dto.getMobileNumber()
        );

        memberRepository.save(member);
    }

    private void verifyMember(MemberCommandDto dto) {
        if(dto.getUserName() == null) {
            throw new IllegalArgumentException("userName cannot be null");
        }

        if(dto.getMobileNumber() == null) {
            throw new IllegalArgumentException("mobileNumber cannot be null");
        }
    }
}
