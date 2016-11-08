package com.promise.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.dto.CreateScopeRequestDto;
import com.promise.auth.dto.GetScopeResponseDto;
import com.promise.service.ScopeServiceInterface;

@RestController
@RequestMapping("/rest")
public class ScopeController {
	
	@Autowired
	private ScopeServiceInterface service;
	
	@PostMapping("/scope")
	public void createScope(@RequestBody CreateScopeRequestDto scope) {
		service.createScope(scope);
	}
	
	@GetMapping("/scope/{id}")
	public GetScopeResponseDto getScope(@PathVariable String id) {
		return service.getScope(id);
	}
}
