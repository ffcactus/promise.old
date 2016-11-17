package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.LoginDto;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.sdk.exception.LoginFailException;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.NoDBInstanceException;

@Component
@Scope("singleton")
public class AuthenticationServiceImpl implements AuthenticationServiceInterface
{

    @Autowired
    private UserServiceInterface userInterface;

    @Override
    public void login(LoginDto loginDto)
            throws InternelErrorException, LoginFailException
    {
        try
        {
            final GetUserResponse userDto = userInterface.getUser(
                    loginDto.getUserName(),
                    loginDto.getPassword().toCharArray());
        }
        catch (final NoSuchAlgorithmException e)
        {
            throw new InternelErrorException(e, PromiseCategory.AA);
        }
        catch (final NoDBInstanceException e)
        {
            // TODO Auto-generated catch block
            throw new LoginFailException(e);
        }

    }

}
