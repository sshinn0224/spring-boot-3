package com.example.sample.members.service;

import com.example.sample.members.presentation.query.dto.MemberResponse;
import com.example.sample.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberFindService {

    private final MemberRepository memberRepository;

    public MemberResponse findOneMember(Long id) {
        return memberRepository.findById(id)
                .map(item -> new MemberResponse(
                        item.getUserName()
                ))
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<MemberResponse> findAllMember() {
        return memberRepository.findAll().stream()
                .map(item -> new MemberResponse(item.getUserName()))
                .toList();
    }


}
