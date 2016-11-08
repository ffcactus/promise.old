package com.promise.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.LoginDto;
import com.promise.auth.Token;
import com.promise.auth.dto.CreateUserRequestDto;
import com.promise.auth.dto.GetUserResponseDto;
import com.promise.auth.exception.LoginFailException;
import com.promise.common.exception.InternelErrorException;
import com.promise.service.UserServiceInterface;

@RestController
@RequestMapping("/rest")
public class AuthController {

	@Autowired
	private UserServiceInterface userInterface;

	@PostMapping("/user")
	void createUser(@RequestBody CreateUserRequestDto dto) {
		userInterface.createUser(dto);
	}

	@GetMapping("/user/{id}")
	GetUserResponseDto getUser(@PathVariable String id) {
		return null;
	}

	@PostMapping("/login")
	Token getLoginSession(@RequestBody LoginDto loginDto)
	        throws LoginFailException,
	        InternelErrorException {
		return null;

	}

	@PostMapping("/auth")
	void auth(@RequestHeader Map<String, String> head) {
		System.out.println(head.toString());
	}
}
