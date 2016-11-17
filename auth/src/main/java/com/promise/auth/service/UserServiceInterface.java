package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;

import com.promise.auth.Token;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
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
     * Get user by token.
     *
     * @param token
     *        The token that can retrieve the user.
     * @return The UserDto.
     */
    public GetUserResponse getUser(Token token);

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
    public GetUserResponse getUser(String username, char[] password)
            throws NoSuchAlgorithmException,
            NoDBInstanceException;
}
