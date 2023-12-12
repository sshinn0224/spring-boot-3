package com.example.sample.presentation.member.query;

import com.example.sample.presentation.member.query.dto.MemberResponse;
import com.example.sample.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FindAllMember {

    private final MemberFindService memberFindService;

    @GetMapping("/member")
    public List<MemberResponse> findAllMembers() {
        return memberFindService.findAllMember();
    }
}
