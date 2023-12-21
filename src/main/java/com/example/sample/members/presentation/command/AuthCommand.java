package com.example.sample.members.presentation.command;


import com.example.sample.members.presentation.command.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthCommand {

    @PostMapping("/authenticate")
    public String authCommand(@RequestBody @Valid LoginRequest dto) {


        return "ok";
    }

}
