package com.promise.auth.sdk.dto;

import java.util.List;

import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseResource;

public class CreateScopeResponse extends PromiseResource
{
    private String name;
    private String description;
    private List<PromiseAccessPoint> accessPointList;

    public CreateScopeResponse()
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<PromiseAccessPoint> getAccessPointList()
    {
        return accessPointList;
    }

    public void setAccessPointList(List<PromiseAccessPoint> accessPointList)
    {
        this.accessPointList = accessPointList;
    }

}
