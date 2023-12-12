package com.example.sample.presentation.member.query;

import com.example.sample.presentation.member.query.dto.MemberResponse;
import com.example.sample.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FindOneMember {

    private final MemberFindService memberFindService;

    @GetMapping("/member/{id}")
    public MemberResponse findMembers(@PathVariable Long id) {
        return memberFindService.findOneMember(id);
    }
}
