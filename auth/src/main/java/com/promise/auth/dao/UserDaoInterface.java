package com.promise.auth.dao;

import java.security.NoSuchAlgorithmException;

import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDbInstanceException;

public interface UserDaoInterface
{

    public boolean isUsernameExist(String username);

    public CreateUserResponse createUser(CreateUserRequest createUserRequest)
            throws NoSuchAlgorithmException;

    public GetUserResponse getUser(String id)
            throws NoDbInstanceException;

    public PromiseUser getUser(String username, HashResult hashResult)
            throws NoDbInstanceException;

    public GetUserListResponse getUserList(int start, int count);

    public void deleteUser(String id);

}
