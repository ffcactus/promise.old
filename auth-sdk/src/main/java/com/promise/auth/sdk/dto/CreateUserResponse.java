package com.promise.auth.sdk.dto;

import java.util.List;

import com.promise.common.PromiseResource;
import com.promise.common.PromiseUser;

public class CreateUserResponse extends PromiseResource
{
    private String username;
    private String email;
    private List<String> scopeUri;

    public CreateUserResponse()
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

    public List<String> getScopeUri()
    {
        return scopeUri;
    }

    public void setScopeUri(List<String> scopeUri)
    {
        this.scopeUri = scopeUri;
    }

    public static CreateUserResponse makeInstance(PromiseUser user)
    {
        final CreateUserResponse ret = new CreateUserResponse();
        PromiseResource.attributeCopy(ret, user);
        ret.setUsername(user.getUsername());
        ret.setEmail(user.getEmail());
        ret.setScopeUri(user.getScopeUri());
        return ret;
    }

}
