package com.promise.common;

import javax.xml.crypto.Data;

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
    private Data createdTime;
    private Data lastUpdatedTime;
    private Data terminatedTime;
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

    public Data getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(Data createdTime)
    {
        this.createdTime = createdTime;
    }

    public Data getLastUpdatedTime()
    {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Data lastUpdatedTime)
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Data getTerminatedTime()
    {
        return terminatedTime;
    }

    public void setTerminatedTime(Data terminatedTime)
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
