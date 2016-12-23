package com.promise.common;

import java.util.Date;

/**
 * PromiseTaskStep represents the steps in a PromiseTask.
 */
public class PromiseTaskStep
{
    private String name;
    private String description;
    private PromiseExecutionState state;
    private int expectedExcutionMs;
    private int percentage;
    private Date createdTime;
    private Date lastUpdatedTime;
    private Date terminatedTime;
    private PromiseExecutionResult result;

    public PromiseTaskStep()
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

    public PromiseExecutionState getState()
    {
        return state;
    }

    public void setState(PromiseExecutionState state)
    {
        this.state = state;
    }

    public int getExpectedExcutionMs()
    {
        return expectedExcutionMs;
    }

    public void setExpectedExcutionMs(int expectedExcutionMs)
    {
        this.expectedExcutionMs = expectedExcutionMs;
    }

    public int getPercentage()
    {
        return percentage;
    }

    public void setPercentage(int percentage)
    {
        this.percentage = percentage;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    public Date getLastUpdatedTime()
    {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime)
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getTerminatedTime()
    {
        return terminatedTime;
    }

    public void setTerminatedTime(Date terminatedTime)
    {
        this.terminatedTime = terminatedTime;
    }

    public PromiseExecutionResult getResult()
    {
        return result;
    }

    public void setResult(PromiseExecutionResult result)
    {
        this.result = result;
    }
}
