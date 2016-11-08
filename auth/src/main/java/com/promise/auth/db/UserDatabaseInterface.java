package com.promise.auth.db;

import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.exception.NoDBInstanceException;

public interface UserDatabaseInterface
{

    public boolean isUsernameExist(String username);

    public void createUser(UserDao user);

    public UserDao getUser(String username, HashResult hashResult)
            throws NoDBInstanceException;
}
