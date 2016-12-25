package com.promise.auth.service;

import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;

public interface TokenServiceInterface
{
    public PromiseToken getToken(PromiseUser user);

    public PromiseUser getUser(PromiseToken token);
}
