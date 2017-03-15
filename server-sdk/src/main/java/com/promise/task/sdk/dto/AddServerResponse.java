package com.promise.task.sdk.dto;

import com.promise.common.exception.PromiseException;

/**
 * GetServerResponse represents the JSON representation of GET a server
 * response.
 */
public class AddServerResponse
{
    private String taskUri;
    private PromiseException exception;

    public AddServerResponse()
    {

    }

    public String getTaskUri()
    {
        return taskUri;
    }

    public void setTaskUri(String taskUri)
    {
        this.taskUri = taskUri;
    }

    public PromiseException getException()
    {
        return exception;
    }

    public void setException(PromiseException exception)
    {
        this.exception = exception;
    }
}
