package com.example.sample.users.presentation.query;

import com.example.sample.users.presentation.query.dto.MemberResponse;
import com.example.sample.users.service.MemberFindService;
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
