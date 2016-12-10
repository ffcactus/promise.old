package com.promise.common;

import java.util.ArrayList;
import java.util.List;

/**
 * PromiseResult represents a execution result.
 * <br>
 * It has many steps.
 * <br>
 * The task result also includes the result of each steps.
 */
public class PromiseExecutionResult
{

    private PromiseExecutionResultState state;
    private String description;
    private List<String> reason;
    private List<String> solution;

    public PromiseExecutionResult()
    {
        state = PromiseExecutionResultState.UNKNOWN;
        reason = new ArrayList<>();
        solution = new ArrayList<>();
    }

    public PromiseExecutionResultState getState()
    {
        return state;
    }

    public void setState(PromiseExecutionResultState state)
    {
        this.state = state;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<String> getReason()
    {
        return reason;
    }

    public void setReason(List<String> reason)
    {
        this.reason = reason;
    }

    public List<String> getSolution()
    {
        return solution;
    }

    public void setSolution(List<String> solution)
    {
        this.solution = solution;
    }

}
