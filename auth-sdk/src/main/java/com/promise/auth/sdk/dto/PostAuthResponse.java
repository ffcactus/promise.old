package com.promise.auth.sdk.dto;

public class PostAuthResponse
{
    private boolean authenticated;
    private boolean authorized;

    public PostAuthResponse()
    {

    }

    public PostAuthResponse(boolean authenticated, boolean authorized)
    {
        this.authenticated = authenticated;
        this.authorized = authorized;
    }

    public boolean isAuthenticated()
    {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated)
    {
        this.authenticated = authenticated;
    }

    public boolean isAuthorized()
    {
        return authorized;
    }

    public void setAuthorized(boolean authorized)
    {
        this.authorized = authorized;
    }

}
