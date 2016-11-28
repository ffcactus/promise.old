package com.promise.auth.db;

import java.util.List;

import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.exception.NoDBInstanceException;

public interface UserDatabaseInterface
{

    public boolean isUsernameExist(String username);

    public UserDao createUser(UserDao user);

    public UserDao getUser(String username, HashResult hashResult)
            throws NoDBInstanceException;

    public List<UserDao> getUser(int start, int count);

}
