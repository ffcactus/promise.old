package com.promise.task.sdk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AddServerRequest represents the JSON representation of POST a server request.
 */
public class AddServerRequest
{
    // The default value of int type is 0 if it's missing in the input.

    @JsonProperty(required = true)
    private String hostname;

    public AddServerRequest()
    {

    }

    public String getHostname()
    {
        return hostname;
    }

    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

}
