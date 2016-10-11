package com.promise.auth.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.LoginDto;
import com.promise.auth.Token;

@RestController
@RequestMapping("/rest")
public class AuthController
{
    @PostMapping("/login")
    Token getLoginSession(@RequestBody LoginDto loginDto)
    {
        return Token.generateToken(loginDto);
    }

    @PostMapping("/auth")
    void auth(@RequestHeader Map<String, String> head)
    {
        System.out.println(head.toString());
    }
}
