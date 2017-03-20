package com.promise.common.dto;

public enum PromiseResponseState
{
    OK ("ok"),
    WARN ("warn"),
    ERROR ("error"),
    ABORT ("abort");

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
