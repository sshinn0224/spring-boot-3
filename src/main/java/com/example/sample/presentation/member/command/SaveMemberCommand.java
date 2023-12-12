package com.example.sample.presentation.member.command;

import com.example.sample.presentation.member.command.dto.MemberCommandDto;
import com.example.sample.service.MemberOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
public class SaveMemberCommand {

    private final MemberOperationService memberOperationService;

    @PostMapping("/member")
    public ResponseEntity<String> save(@RequestBody MemberCommandDto member) {
        memberOperationService.save(member);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success..");

    }
}
