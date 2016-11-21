package com.promise.auth.sdk.dto;

public class PostLoginRequest {

    private String userName;
    private String password;
    private String domain;

    public PostLoginRequest()
    {
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }
}
