package com.promise.service;

import com.promise.auth.dto.CreateScopeRequestDto;
import com.promise.auth.dto.GetScopeResponseDto;

public interface ScopeServiceInterface {

	public void createScope(CreateScopeRequestDto dto);

	public GetScopeResponseDto getScope(String id);

	public void updateScope(String id, CreateScopeRequestDto dto);
}
