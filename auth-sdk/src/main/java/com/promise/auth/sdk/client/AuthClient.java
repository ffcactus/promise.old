package com.promise.auth.sdk.client;

import org.springframework.http.ResponseEntity;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseToken;

public class AuthClient
{
    private final static String URL_HEAD = "http://localhost";

    public static ResponseEntity<PostAuthResponse> aa(PromiseToken token, PromiseAccessPoint accessPoint)
    {
        return PromiseClient.httpPost(
                URL_HEAD + "/rest/auth",
                null,
                PromiseClient.makeHeader(token, accessPoint),
                PostAuthResponse.class);
    }
}
