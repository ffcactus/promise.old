package com.promise.auth.service;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseToken;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.LoginFailureException;

public interface AuthServiceInterface
{
    public PostLoginResponse login(PostLoginRequest request)
            throws InternelErrorException, LoginFailureException;

    public PostAuthResponse auth(PromiseToken token, PromiseAccessPoint accessPoint);
}
