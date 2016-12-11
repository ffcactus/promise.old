package com.promise.task.sdk.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PostTaskRequest represents the JSON representation of POST a task request.
 */
public class PostTaskRequest
{
    // The default value of int type is 0 if it's missing in the input.

    @JsonProperty(required = true)
    private String name;
    private String description;
    private String createdByUri;
    private int expectedExcutionMs;
    private int timeoutMs;
    private List<PostTaskStepRequest> stepList;

    public PostTaskRequest()
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

    public String getCreatedByUri()
    {
        return createdByUri;
    }

    public void setCreatedByUri(String createdByUri)
    {
        this.createdByUri = createdByUri;
    }

    public int getExpectedExcutionMs()
    {
        return expectedExcutionMs;
    }

    public void setExpectedExcutionMs(int expectedExcutionMs)
    {
        this.expectedExcutionMs = expectedExcutionMs;
    }

    public int getTimeoutMs()
    {
        return timeoutMs;
    }

    public void setTimeoutMs(int timeoutMs)
    {
        this.timeoutMs = timeoutMs;
    }

    public List<PostTaskStepRequest> getStepList()
    {
        return stepList;
    }

    public void setStepList(List<PostTaskStepRequest> stepList)
    {
        this.stepList = stepList;
    }

}
