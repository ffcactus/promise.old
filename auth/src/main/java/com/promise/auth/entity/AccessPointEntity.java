package com.promise.auth.entity;

import java.util.UUID;

public class AccessPointEntity
{

    private UUID id;
    private String type;
    private String value;

    public AccessPointEntity()
    {

    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}