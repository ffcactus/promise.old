package com.promise.service;

import com.promise.auth.dto.CreateScopeRequestDto;

public interface ScopeServiceInterface {

	public void createScope(CreateScopeRequestDto dto);

	public void getScope(String id);

	public void updateScope(String id, CreateScopeRequestDto dto);
}
