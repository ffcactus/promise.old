package com.promise.auth.entity;

import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;

public interface TokenDbInterface
{

    PromiseToken makeAndCacheToken(PromiseUser user);
}
