package com.promise.auth.sdk.dto;

import java.util.List;

import com.promise.auth.sdk.AccessPoint;
import com.promise.common.PromiseResource;

public class GetScopeResponse extends PromiseResource
{
    private String name;
    private String description;
    private List<AccessPoint> accessPointList;

    public GetScopeResponse()
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

    public List<AccessPoint> getAccessPointList()
    {
        return accessPointList;
    }

    public void setAccessPointList(List<AccessPoint> accessPointList)
    {
        this.accessPointList = accessPointList;
    }
}
