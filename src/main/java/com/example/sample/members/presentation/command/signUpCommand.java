package com.example.sample.members.presentation.command;

import com.example.sample.members.presentation.command.dto.signUpDto;
import com.example.sample.members.service.MemberCommandService;
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

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid signUpDto member) {
        memberCommandService.save(member);

        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

}
