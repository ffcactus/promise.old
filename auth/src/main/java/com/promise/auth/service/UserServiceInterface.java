package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;

import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;
import com.promise.common.exception.NoDBInstanceException;

public interface UserServiceInterface
{

    /**
     * Create a user.
     *
     * @param user The DTO that represents the user to create.
     * @return The DTO that represents the user created.
     */
    public CreateUserResponse createUser(CreateUserRequest user);

    /**
     * Get user by ID.
     *
     * @param id the ID of the user.
     * @return The DTO that represents the user or null if it can't be found.
     */
    public GetUserResponse getUser(String id);

    /**
     * Get user by token.
     *
     * @param token
     *        The token that can retrieve the user.
     * @return The UserDto.
     */
    public GetUserResponse getUser(PromiseToken token);

    /**
     * Get user by username and password.
     *
     * @param username
     *        The username of the user.
     * @param password
     *        The password of the user.
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoDBInstanceException
     */
    public PromiseUser getUser(String username, char[] password)
            throws NoSuchAlgorithmException,
            NoDBInstanceException;

    /**
     * Get user list.
     *
     * @param start
     * @param count
     * @return
     */
    public GetUserListResponse getUserList(int start, int count);

    /**
     * Delete User by ID.
     *
     * @param id The id of the user to delete.
     * @throws NoDBInstanceException If the scope is unable to find.
     */
    public void deleteUser(String id)
            throws NoDBInstanceException;
}
