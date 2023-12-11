package com.example.sample.presentation.user.query;

import com.example.sample.presentation.user.query.view.MemberResponse;
import com.example.sample.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FindAllMember {

    private final MemberService memberService;

    @GetMapping("/member")
    public List<MemberResponse> findAllMembers() {
        return memberService.findAllMember();
    }
}
