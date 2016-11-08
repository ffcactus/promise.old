package com.promise.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.dto.CreateUserRequestDto;
import com.promise.auth.dto.GetUserResponseDto;
import com.promise.service.UserServiceInterface;

@RestController
@RequestMapping("/rest")
public class UserController {

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
}
