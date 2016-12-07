package com.promise.auth.sdk.dto;

import java.util.List;

/**
 * The object that represent the input to create a user.
 */
public class CreateUserRequest
{
    private String username;
    private char[] password;
    private String email;
    private List<String> scopeUri;

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

    public List<String> getScopeUri()
    {
        return scopeUri;
    }

    public void setScopeUri(List<String> scopeUri)
    {
        this.scopeUri = scopeUri;
    }

    public boolean isValidRequest()
    {
        if (scopeUri == null || username == null || !isValidPassword(password))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean isValidPassword(char[] password)
    {
        return true;
    }
}