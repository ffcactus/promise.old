package com.promise.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.service.AuthServiceStatistic;
import com.promise.auth.service.UserServiceInterface;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InvalidRequestBodyException;

@RestController
@RequestMapping("/rest")
public class UserController
{
    @Autowired
    private AuthServiceStatistic statistic;

    @Autowired
    private UserServiceInterface userService;

    @PostMapping("/user")
    ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest dto)
            throws InvalidRequestBodyException
    {
        statistic.recodeUri("/user POST");
        if (!dto.isValidRequest())
        {
            throw new InvalidRequestBodyException(null, PromiseCategory.USER);
        }
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    GetUserResponse getUser(@PathVariable String id)
    {
        return null;
    }
}
