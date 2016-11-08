package com.promise.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;

import com.promise.auth.Token;
import com.promise.auth.db.UserDao;
import com.promise.auth.db.UserDatabaseInterface;
import com.promise.auth.dto.CreateUserRequestDto;
import com.promise.auth.dto.GetUserResponseDto;
import com.promise.auth.util.PasswordUtil;
import com.promise.auth.util.PasswordUtil.HashResult;
import com.promise.common.exception.NoDBInstanceException;

public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private UserDatabaseInterface userDatabaseInterface;

	@Override
	public void createUser(CreateUserRequestDto userDto) {
		try {
			userDatabaseInterface.createUser(createUserRequestDto2Dao(userDto));
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public GetUserResponseDto getUser(Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetUserResponseDto getUser(String username, char[] password)
	        throws NoSuchAlgorithmException, NoDBInstanceException {
		HashResult hashResult = PasswordUtil.hashPassword(password);
		UserDao userDao = userDatabaseInterface.getUser(username, hashResult);
		return dao2GetUserResponseDto(userDao);
	}

	/**
	 * Convert DAO to GetUserResponseDto.
	 * 
	 * @param input
	 *            UserDao
	 * @return GetUserResponseDto
	 */
	private GetUserResponseDto dao2GetUserResponseDto(UserDao input) {
		final GetUserResponseDto ret = new GetUserResponseDto();
		ret.setUsername(input.getUsername());
		ret.setEmail(input.getEmail());
		ret.setScopeUri(input.getScopeUri());
		ret.setId(input.getId());
		return ret;
	}

	/**
	 * Convert CreateUserRequestDto to DAO
	 *
	 * @param input
	 *            CreateUserRequestDto
	 * @return UserDao
	 * @throws NoSuchAlgorithmException
	 */
	private UserDao createUserRequestDto2Dao(CreateUserRequestDto input) throws NoSuchAlgorithmException {
		final UserDao ret = new UserDao();
		ret.setUsername(input.getUsername());
		ret.setEmail(input.getEmail());
		final HashResult hashResult = PasswordUtil.hashPassword(input.getPassword());
		ret.setSalt(hashResult.getSalt());
		ret.setHash(hashResult.getHash());
		ret.setScopeUri(input.getScopeUri());
		ret.setId(null);
		return ret;
	}
	
}
