package com.promise.auth.service;

import com.promise.auth.dto.CreateScopeRequest;
import com.promise.auth.dto.CreateScopeResponse;
import com.promise.auth.dto.GetScopeResponse;
import com.promise.common.exception.NoDBInstanceException;

public interface ScopeServiceInterface
{

    public CreateScopeResponse createScope(CreateScopeRequest dto);

    public GetScopeResponse getScope(String id)
            throws NoDBInstanceException;

    public void updateScope(String id, CreateScopeRequest dto);
}
