package com.promise.auth.service;

import com.promise.auth.LoginDto;
import com.promise.auth.sdk.exception.LoginFailException;
import com.promise.common.exception.InternelErrorException;

public interface AuthenticationServiceInterface
{
    public void login(LoginDto loginDto)
            throws InternelErrorException, LoginFailException;
}
