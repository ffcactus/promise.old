package com.promise.auth;

import java.util.List;

public class User
{
    private String id;
    private String username;
    private String email;
    private List<Scope> scope;

    public User()
    {

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Scope> getScope()
    {
        return scope;
    }

    public void setScope(List<Scope> scope)
    {
        this.scope = scope;
    }

}
