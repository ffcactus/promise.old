package com.promise.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.promise.common.constant.PromiseCategory;

@JsonInclude(Include.NON_NULL)
public class PromiseOperationResponse
{
    private PromiseResponseState state;
    private String uri;
    private PromiseCategory category;
    private String name;
    private String description;
    private String[] reason;
    private String[] solution;

    public PromiseOperationResponse()
    {

    }

    public PromiseResponseState getState()
    {
        return state;
    }

    public void setState(PromiseResponseState state)
    {
        this.state = state;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public PromiseCategory getCategory()
    {
        return category;
    }

    public void setCategory(PromiseCategory category)
    {
        this.category = category;
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

    public String[] getReason()
    {
        return reason;
    }

    public void setReason(String[] reason)
    {
        this.reason = reason;
    }

    public String[] getSolution()
    {
        return solution;
    }

    public void setSolution(String[] solution)
    {
        this.solution = solution;
    }

}
