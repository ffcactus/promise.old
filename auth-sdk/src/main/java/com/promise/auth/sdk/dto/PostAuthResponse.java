package com.promise.auth.sdk.dto;

public class PostAuthResponse
{
    public static final String ACCEPT = "accept";
    public static final String FORBIDDEN = "forbid";
    private String authenticationResult;
    private String authorizationResult;

    public PostAuthResponse()
    {

    }

    public String getAuthenticationResult()
    {
        return authenticationResult;
    }

    public void setAuthenticationResult(String authenticationResult)
    {
        this.authenticationResult = authenticationResult;
    }

    public String getAuthorizationResult()
    {
        return authorizationResult;
    }

    public void setAuthorizationResult(String authorizationResult)
    {
        this.authorizationResult = authorizationResult;
    }
}
