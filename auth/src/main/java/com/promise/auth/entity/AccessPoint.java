package com.promise.auth.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccessPoint
{
    @Column(name = "type")
    private String type;

    @Column(name = "\"value\"")
    private String value;

    public AccessPoint()
    {

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
