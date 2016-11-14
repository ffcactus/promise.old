package com.promise.auth.dto;

import com.promise.common.PromiseResource;
import com.promise.common.constant.PromiseCategory;

/**
 * The object to represent the response of getting a user.
 *
 *
 */
public class GetUserResponse extends PromiseResource
{
    private String username;
    private String email;
    private String scopeUri;

    public GetUserResponse()
    {
        setCategory(PromiseCategory.USER);
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

}
