package com.promise.auth.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promise.common.PromiseHttpRequest;

public class CreateScopeRequest extends PromiseHttpRequest
{
    private String name;
    private String description;
    private List<AccessPoint> accessPointList;

    public CreateScopeRequest()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<AccessPoint> getAccessPointList()
    {
        return accessPointList;
    }

    public void setAccessPointList(List<AccessPoint> accessPointList)
    {
        this.accessPointList = accessPointList;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    @JsonIgnore
    public boolean isValidRequest()
    {
        if (accessPointList == null || name == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
