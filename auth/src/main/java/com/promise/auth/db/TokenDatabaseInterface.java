package com.promise.auth.db;

import com.promise.auth.Token;
import com.promise.auth.User;

public interface TokenDatabaseInterface
{

    public Token createToken(User user);
}
