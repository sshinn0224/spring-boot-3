package com.example.sample.members.presentation.command;

import com.example.sample.members.presentation.command.dto.signUpDto;
import com.example.sample.members.service.MemberOperationService;
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
public class signUpCommand {

    private final MemberOperationService memberOperationService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid signUpDto member) {
        memberOperationService.save(member);

        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

}
