package com.promise.task.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.promise.common.PromiseExecutionResultState;

@Embeddable
public class ExecutionResult
{
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private PromiseExecutionResultState state;

    @Column(name = "description")
    private String description;

    @ElementCollection
    private List<String> reason;

    @ElementCollection
    private List<String> solution;

    public ExecutionResult()
    {

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
