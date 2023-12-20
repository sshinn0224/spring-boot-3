package com.example.sample.users.presentation.command;

import com.example.sample.users.presentation.command.dto.MemberRegistrationRequest;
import com.example.sample.users.presentation.command.dto.MemberRegistrationResponse;
import com.example.sample.users.service.MemberOperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Validated
public class SaveMember {

    private final MemberOperationService memberOperationService;

    @PostMapping("/member")
    public ResponseEntity<?> save(@RequestBody @Valid MemberRegistrationRequest member) {
        memberOperationService.save(member);

        return ResponseEntity.ok("success");

    }
}
