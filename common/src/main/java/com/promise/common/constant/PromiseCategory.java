package com.promise.common.constant;

public enum PromiseCategory
{
    TASK ("task"),
    USER ("user"),
    SCOPE ("scope"),
    AA ("auth");

    private final String value;

    PromiseCategory(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
