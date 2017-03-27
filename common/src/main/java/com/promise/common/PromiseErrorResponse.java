package com.promise.common;

import com.promise.common.exception.PromiseException;

public class PromiseErrorResponse
{
    private String name;
    private String description;
    private String[] reason;
    private String[] solution;

    public PromiseErrorResponse()
    {

    }

    public static PromiseErrorResponse makeInstance(PromiseException e)
    {
        final PromiseErrorResponse ret = new PromiseErrorResponse();
        ret.setName(e.getName());
        ret.setDescription(e.getDescription());
        ret.setReason(e.getReason());
        ret.setSolution(e.getSolution());
        return ret;
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
