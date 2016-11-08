package com.promise.auth.dto;

/**
 * The object that represent the input to create a user.
 */
public class CreateUserRequest
{
    private String username;
    private char[] password;
    private String email;
    private String scopeUri;

    public CreateUserRequest()
    {

    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public char[] getPassword()
    {
        return password;
    }

    public void setPassword(char[] password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getScopeUri()
    {
        return scopeUri;
    }

    public void setScopeUri(String scopeUri)
    {
        this.scopeUri = scopeUri;
    }
}
