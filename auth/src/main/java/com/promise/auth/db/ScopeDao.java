package com.promise.auth.db;

public class ScopeDao
{
    public enum ScopeType
    {
        URL,
        SUB_SCOPE
    }

    public static final String RESOURCE = "RESOURCE";
    public static final String SCOPE = "SCOPE";

    private String type;
    private String value;

    public ScopeDao(String type, String value)
    {
        this.type = type;
        this.value = value;
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
