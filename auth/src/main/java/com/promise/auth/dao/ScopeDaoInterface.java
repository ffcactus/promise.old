package com.promise.auth.dao;

import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.common.PromiseDaoInterface;

public interface ScopeDaoInterface extends PromiseDaoInterface<CreateScopeRequest, GetScopeResponse>
{
    boolean isScopeExsit(String uri);

    public GetScopeListResponse getScopeList(int start, int count);

}
