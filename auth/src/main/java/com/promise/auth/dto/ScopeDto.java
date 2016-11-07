package com.promise.auth.dto;

/**
 * The object that use in the transaction between the service and client.
 */
public class ScopeDto
{
    private String type;
    private String value;

    public ScopeDto()
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
