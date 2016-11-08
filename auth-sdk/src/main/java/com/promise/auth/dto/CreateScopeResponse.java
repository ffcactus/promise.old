package com.promise.auth.dto;

import java.util.ArrayList;

public class CreateScopeResponse
{
    private String id;
    private String name;
    private String description;
    private ArrayList<AccessPoint> accessPointList;

    public CreateScopeResponse()
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

    public ArrayList<AccessPoint> getAccessPointList()
    {
        return accessPointList;
    }

    public void setAccessPointList(ArrayList<AccessPoint> accessPointList)
    {
        this.accessPointList = accessPointList;
    }

}
