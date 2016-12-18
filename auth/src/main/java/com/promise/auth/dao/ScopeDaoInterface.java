package com.promise.auth.dao;

import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.common.exception.NoDbInstanceException;

public interface ScopeDaoInterface
{
    CreateScopeResponse createScope(CreateScopeRequest createScopeRequest);

    boolean isScopeExsit(String uri);

    GetScopeResponse getScope(String id)
            throws NoDbInstanceException;

    public void deleteScope(String id)
            throws NoDbInstanceException;

    public GetScopeListResponse getScopeList(int start, int count);

}
