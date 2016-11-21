package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.NoDBInstanceException;

@Component
@Scope("singleton")
public class AuthenticationServiceImpl implements AuthenticationServiceInterface
{

    @Autowired
    private UserServiceInterface userService;
    
    @Autowired
    private TokenServiceInterface tokenService;
    

    @Override
    public PostLoginResponse login(PostLoginRequest request)
            throws InternelErrorException
    {
        try
        {
            final PromiseUser user = userService.getUser(
            		request.getUserName(),
            		request.getPassword().toCharArray());
            PromiseToken promiseToken = tokenService.getToken(user);
            PostLoginResponse response = new PostLoginResponse();
            response.setToken(promiseToken.getValue());
            return response;
            
        }
        catch (final NoSuchAlgorithmException e)
        {
            throw new InternelErrorException(e, PromiseCategory.AA);
        }
        catch (final NoDBInstanceException e)
        {
            // TODO Auto-generated catch block
        }
		return null;

    }

	@Override
	public PostAuthResponse auth(PromiseToken token, PromiseAccessPoint accessPoint) {
		// TODO Auto-generated method stub
		return null;
	}

}
