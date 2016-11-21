package com.promise.auth.service;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseToken;
import com.promise.common.exception.InternelErrorException;

public interface AuthenticationServiceInterface
{
    public PostLoginResponse login(PostLoginRequest request)
            throws InternelErrorException;
    
    public PostAuthResponse auth(PromiseToken token, PromiseAccessPoint accessPoint);
}
