package com.promise.task.sdk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PostTaskStepRequest represents the JSON representation as task step in a POST
 * a task request.
 */
public class PostTaskStepRequest
{

    @JsonProperty(required = true)
    private String name;
    private String description;
    private int expectedExcutionMs;

    public PostTaskStepRequest()
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getExpectedExcutionMs()
    {
        return expectedExcutionMs;
    }

    public void setExpectedExcutionMs(int expectedExcutionMs)
    {
        this.expectedExcutionMs = expectedExcutionMs;
    }
}
