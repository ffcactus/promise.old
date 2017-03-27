package com.promise.auth.sdk.client;

import org.springframework.http.ResponseEntity;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseToken;

public class AuthClient
{
    public static ResponseEntity<PostAuthResponse> aa(PromiseToken token, PromiseAccessPoint accessPoint)
    {
        return PromiseClient.httpPost(
                PromiseClient.URL_HEAD + "/rest/private/auth",
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
                PromiseClient.URL_HEAD + "/rest/login",
                request,
                null,
                PostLoginResponse.class);
    }
}
