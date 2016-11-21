package com.promise.common;

public class PromiseToken
{
    private final String value;

    public PromiseToken(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
    
    public boolean equals(Object other)
    {
        return (other != null && other instanceof PromiseToken && ((PromiseToken) other).getValue().equals(value));
    }
}
