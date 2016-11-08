package com.promise.service;

import com.promise.auth.LoginDto;
import com.promise.auth.exception.LoginFailException;
import com.promise.common.exception.InternelErrorException;

public interface AuthenticationServiceInterface
{
    public void login(LoginDto loginDto) throws InternelErrorException, LoginFailException;
}
