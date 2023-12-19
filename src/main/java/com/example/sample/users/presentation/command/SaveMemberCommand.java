package com.example.sample.users.presentation.command;

import com.example.sample.common.types.ResponseBodyMessageTypes;
import com.example.sample.users.presentation.command.dto.MemberCommandDto;
import com.example.sample.users.service.MemberOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SaveMemberCommand {

    private final MemberOperationService memberOperationService;

    @PostMapping("/member")
    public ResponseEntity<String> save(@RequestBody MemberCommandDto member) {
        memberOperationService.save(member);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBodyMessageTypes.SUCCESS.getMessage());

    }
}
