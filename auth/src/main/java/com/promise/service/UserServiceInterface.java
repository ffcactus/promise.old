package com.promise.service;

import java.security.NoSuchAlgorithmException;

import com.promise.auth.Token;
import com.promise.auth.dto.CreateUserRequestDto;
import com.promise.auth.dto.GetUserResponseDto;
import com.promise.common.exception.NoDBInstanceException;

public interface UserServiceInterface {

	/**
	 * Create a user.
	 * 
	 * @param user
	 *            The UserDto
	 */
	public void createUser(CreateUserRequestDto user);

	/**
	 * Get user by token.
	 * 
	 * @param token
	 *            The token that can retrieve the user.
	 * @return The UserDto.
	 */
	public GetUserResponseDto getUser(Token token);

	/**
	 * Get user by username and password.
	 * 
	 * @param username
	 *            The username of the user.
	 * @param password
	 *            The password of the user.
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoDBInstanceException
	 */
	public GetUserResponseDto getUser(String username, char[] password)
	        throws NoSuchAlgorithmException,
	        NoDBInstanceException;
}
