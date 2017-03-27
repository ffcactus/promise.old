package com.promise.common.response;

public enum PromiseResponseState
{
    OK ("ok"),
    WARN ("warn"),
    ERROR ("error");

    private final String value;

    PromiseResponseState(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
