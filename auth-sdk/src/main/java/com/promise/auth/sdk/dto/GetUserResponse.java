package com.promise.auth.sdk.dto;

import java.util.List;

import com.promise.common.PromiseResource;
import com.promise.common.PromiseUser;
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
    private List<String> scopeUri;

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

    public List<String> getScopeUri()
    {
        return scopeUri;
    }

    public void setScopeUri(List<String> scopeUri)
    {
        this.scopeUri = scopeUri;
    }

    public static GetUserResponse makeInstance(PromiseUser user)
    {
        final GetUserResponse ret = new GetUserResponse();
        PromiseResource.attributeCopy(ret, user);
        ret.setUsername(user.getUsername());
        ret.setEmail(user.getEmail());
        ret.setScopeUri(user.getScopeUri());
        return ret;
    }

}
