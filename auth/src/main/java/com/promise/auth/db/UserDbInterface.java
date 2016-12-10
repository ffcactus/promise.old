package com.promise.auth.db;

import java.util.List;

import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.exception.NoDbInstanceException;

public interface UserDbInterface
{

    public boolean isUsernameExist(String username);

    public UserDao createUser(UserDao user);

    public UserDao getUser(String username, HashResult hashResult)
            throws NoDbInstanceException;

    public UserDao getUser(String id)
            throws NoDbInstanceException;

    public List<UserDao> getUser(int start, int count);

    public void deleteUser(String id)
            throws NoDbInstanceException;

}
