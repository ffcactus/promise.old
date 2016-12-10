package com.promise.auth.sdk.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.promise.common.PromiseAccessPoint;

public class CreateScopeRequest
{
    @JsonProperty(required = true)
    private String name;
    private String description;
    @JsonProperty(required = true)
    private List<PromiseAccessPoint> accessPointList;

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

    public List<PromiseAccessPoint> getAccessPointList()
    {
        return accessPointList;
    }

    public void setAccessPointList(List<PromiseAccessPoint> accessPointList)
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

}
