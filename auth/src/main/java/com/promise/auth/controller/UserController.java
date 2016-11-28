package com.promise.auth.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.client.AuthClient;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.service.AuthServiceStatistic;
import com.promise.auth.service.UserServiceInterface;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseToken;
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

    private static final Logger log = Logger.getLogger(UserController.class);

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

    @GetMapping("/user")
    ResponseEntity<GetUserListResponse> getUserList(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "count", defaultValue = "0") int count)
            throws InvalidRequestBodyException
    {
        log.info("HTTP GET /rest/user");
        if (start < 0 || count < 0)
        {
            // TODO Invalid URL?
            throw new InvalidRequestBodyException(null, PromiseCategory.USER);
        }
        return new ResponseEntity<>(userService.getUserList(start, count), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    GetUserResponse getUser(@PathVariable String id)
    {
        AuthClient.aa(new PromiseToken("token"), new PromiseAccessPoint());
        return null;
    }
}
