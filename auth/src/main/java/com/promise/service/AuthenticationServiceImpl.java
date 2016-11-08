package com.promise.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;

import com.promise.auth.LoginDto;
import com.promise.auth.Token;
import com.promise.auth.dto.GetUserResponseDto;
import com.promise.auth.exception.LoginFailException;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.NoDBInstanceException;

public class AuthenticationServiceImpl implements AuthenticationServiceInterface {

	@Autowired
	private UserServiceInterface userInterface;
	
	@Override
    public void login(LoginDto loginDto) throws InternelErrorException, LoginFailException
    {
		try {
			GetUserResponseDto userDto = userInterface.getUser(loginDto.getUserName(),
			        loginDto.getPassword().toCharArray());
		} catch (NoSuchAlgorithmException e) {
			throw new InternelErrorException(e, PromiseCategory.AA);
		} catch (NoDBInstanceException e) {
			// TODO Auto-generated catch block
			throw new LoginFailException(e);
		}


    }

}
