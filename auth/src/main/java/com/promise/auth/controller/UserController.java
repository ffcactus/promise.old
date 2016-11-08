package com.promise.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.dto.CreateUserRequest;
import com.promise.auth.dto.GetUserResponse;
import com.promise.auth.service.UserServiceInterface;

@RestController
@RequestMapping("/rest")
public class UserController
{

    @Autowired
    private UserServiceInterface userInterface;

    @PostMapping("/user")
    void createUser(@RequestBody CreateUserRequest dto)
    {
        userInterface.createUser(dto);
    }

    @GetMapping("/user/{id}")
    GetUserResponse getUser(@PathVariable String id)
    {
        return null;
    }
}
