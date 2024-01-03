package com.example.sample.members.presentation.command;

import com.example.sample.members.presentation.command.dto.MemberRegistrationRequest;
import com.example.sample.members.service.MemberOperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Validated
public class RegisterCommand {

    private final MemberOperationService memberOperationService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> save(@RequestBody @Valid MemberRegistrationRequest member) {
        memberOperationService.save(member);

        return ResponseEntity.ok("success");
    }
}
