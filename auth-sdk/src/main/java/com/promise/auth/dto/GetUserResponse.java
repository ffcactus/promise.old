package com.promise.auth.dto;

/**
 * The object to represent the response of getting a user.
 * 
 * @author b00392874
 *
 */
public class GetUserResponse
{

    private String id;
    private String username;
    private String email;
    private String scopeUri;

    public GetUserResponse()
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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
