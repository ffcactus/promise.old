package com.promise.auth.service;

import com.promise.auth.dto.CreateScopeRequest;
import com.promise.auth.dto.GetScopeResponse;

public interface ScopeServiceInterface
{

    public void createScope(CreateScopeRequest dto);

    public GetScopeResponse getScope(String id);

    public void updateScope(String id, CreateScopeRequest dto);
}
