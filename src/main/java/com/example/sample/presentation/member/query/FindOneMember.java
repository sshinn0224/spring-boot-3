package com.example.sample.presentation.member.query;

import com.example.sample.presentation.member.query.view.MemberResponse;
import com.example.sample.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FindOneMember {

    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public MemberResponse findMembers(@PathVariable Long id) {
        return memberService.findOneMember(id);
    }
}
