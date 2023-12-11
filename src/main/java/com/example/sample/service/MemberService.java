package com.example.sample.service;

import com.example.sample.presentation.user.query.view.MemberResponse;
import com.example.sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse findOneMember(Long id) {
        return memberRepository.findById(id)
                .map(item -> new MemberResponse(
                        item.getUserName()
                ))
                .orElseThrow(IllegalArgumentException::new);
    }


}
