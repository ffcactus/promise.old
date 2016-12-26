package com.promise.auth.dao;

import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.PromiseDaoInterface;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDbInstanceException;

public interface UserDaoInterface extends PromiseDaoInterface<CreateUserRequest, GetUserResponse>
{

    public boolean isUsernameExist(String username);

    public PromiseUser getUser(String username, HashResult hashResult)
            throws NoDbInstanceException;

    public GetUserListResponse getUserList(int start, int count);

}
