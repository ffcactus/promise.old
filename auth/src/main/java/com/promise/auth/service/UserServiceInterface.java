package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.common.PromiseUser;
import com.promise.common.dto.PromiseHttpResponse;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;

public interface UserServiceInterface
{

    /**
     * Create a user.
     *
     * @param user The DTO that represents the user to create.
     * @return The DTO that represents the user created.
     * @throws NoDbInstanceException
     * @throws NoSuchAlgorithmException
     */
    public PromiseHttpResponse createUser(CreateUserRequest user)
            throws InvalidRequestBodyException;

    /**
     * Get user by ID.
     *
     * @param id The ID of the user.
     * @return The DTO that represents the result.
     */
    public PromiseHttpResponse getUser(String id);

    /**
     * Get user by token.
     *
     * @param token
     *        The token that can retrieve the user.
     * @return The UserDto.
     * @throws NoDbInstanceException If the user can't be found.
     */
    //    public GetUserResponse getUser(PromiseToken token)
    //            throws NoDbInstanceException;

    /**
     * Get user by username and password.
     *
     * @param username
     *        The username of the user.
     * @param password
     *        The password of the user.
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoDbInstanceException If the user can't be found.
     */
    public PromiseUser getUser(String username, char[] password)
            throws NoSuchAlgorithmException,
            NoDbInstanceException;

    /**
     * Get user list.
     *
     * @param start
     * @param count
     * @return
     */
    public GetUserListResponse getUserList(Optional<Integer> start, Optional<Integer> count);

    /**
     * Delete User by ID.
     *
     * @param id The id of the user to delete.
     */
    public PromiseHttpResponse deleteUser(String id);
}
