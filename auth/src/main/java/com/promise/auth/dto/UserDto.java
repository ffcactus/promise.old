package com.promise.auth.dto;

import java.util.List;

/**
 * The object that represent the input to create a user.
 */
public class UserDto
{
    private String username;
    private char[] password;
    private String email;
    private List<ScopeDto> scopeList;

    public UserDto()
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

    public List<ScopeDto> getScopeList()
    {
        return scopeList;
    }

    public void setScopeList(List<ScopeDto> scopeList)
    {
        this.scopeList = scopeList;
    }
}
