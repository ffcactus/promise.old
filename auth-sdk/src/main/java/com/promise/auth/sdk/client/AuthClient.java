package com.promise.auth.sdk.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseToken;

public class AuthClient
{
    public static final String LOCAL_TOKEN_FILE = "/tmp/promise_local_token";
    private final static String URL_HEAD = "http://localhost";

    public PromiseToken getModuleToken(String ModuleName)
    {
        final Path file = Paths.get(LOCAL_TOKEN_FILE);
        try
        {
            final List<String> content = Files.readAllLines(file, StandardCharsets.UTF_8);
            if ((content != null) && (content.size() > 0))
            {
                return new PromiseToken(content.get(0));
            }
            else
            {
                return null;
            }
        }
        catch (final IOException e)
        {
            return null;
        }
    }

    public static ResponseEntity<PostAuthResponse> aa(PromiseToken token, PromiseAccessPoint accessPoint)
    {
        return PromiseClient.httpPost(
                URL_HEAD + "/rest/private/auth",
                null,
                PromiseClient.makeHeader(token, accessPoint),
                PostAuthResponse.class);
    }

    public static ResponseEntity<PostLoginResponse> login(String username, String password, String domain)
    {
        final PostLoginRequest request = new PostLoginRequest();
        request.setUserName(username);
        request.setPassword(password);
        request.setDomain(domain);
        return PromiseClient.httpPost(
                URL_HEAD + "/rest/login",
                request,
                null,
                PostLoginResponse.class);
    }
}
