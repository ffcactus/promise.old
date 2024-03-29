package com.promise.auth.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.LoginFailureException;
import com.promise.common.exception.NoDbInstanceException;

@Component
@Scope("singleton")
@Transactional
public class AuthServiceImpl implements AuthServiceInterface
{
    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private TokenServiceInterface tokenService;

    private static PromiseToken localToken;

    @PostConstruct
    private void postConstruct()
    {
        try
        {
            localToken = new PromiseToken(UUID.randomUUID().toString());
            final Path file = Paths.get(PromiseClient.LOCAL_TOKEN_FILE);
            final List<String> lines = Arrays.asList(localToken.getValue());
            Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
            log.info("Local token created.");
        }
        catch (final IOException e)
        {
            log.error("Failed to create a local token");
        }
    }

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
            throw new InternelErrorException();
        }
        catch (final NoDbInstanceException e)
        {
            log.info("Failed to login. Invalied username or password.");
            throw new LoginFailureException();
        }
    }

    @Override
    public PostAuthResponse auth(PromiseToken token, PromiseAccessPoint accessPoint)
    {
        final PostAuthResponse response = new PostAuthResponse();
        if (localToken.equals(token.getLocalToken()))
        {
            response.setAuthenticated(true);
            response.setAuthorized(true);
        }
        else if (tokenService.getUser(token) != null)
        {
            response.setAuthenticated(true);
            response.setAuthorized(true);
        }
        // TODO remove me.
        else if (token.getValue().equals("pass"))
        {
            response.setAuthenticated(true);
            response.setAuthorized(true);
        }
        else
        {
            response.setAuthenticated(false);
            response.setAuthorized(false);
        }
        return response;
    }

}
