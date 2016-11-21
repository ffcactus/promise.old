package com.promise.auth.service;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
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
import com.promise.common.exception.LoginFailureException;
import com.promise.common.exception.NoDBInstanceException;

@Component
@Scope("singleton")
public class AuthServiceImpl implements AuthServiceInterface
{

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private TokenServiceInterface tokenService;

    private static Logger log = Logger.getLogger(AuthServiceImpl.class);

    @Override
    public PostLoginResponse login(PostLoginRequest request)
            throws InternelErrorException, LoginFailureException
    {
        try
        {
            final PromiseUser user = userService.getUser(
                    request.getUserName(),
                    request.getPassword().toCharArray());
            final PromiseToken promiseToken = tokenService.getToken(user);
            final PostLoginResponse response = new PostLoginResponse();
            response.setToken(promiseToken.getValue());
            return response;

        }
        catch (final NoSuchAlgorithmException e)
        {
            log.error("Failed to find a algorithm.");
            throw new InternelErrorException(PromiseCategory.AA);
        }
        catch (final NoDBInstanceException e)
        {
            log.info("Failed to login. Invalied username or password.");
            throw new LoginFailureException();
        }
    }

    @Override
    public PostAuthResponse auth(PromiseToken token, PromiseAccessPoint accessPoint)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
