package com.promise.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PromiseTask represents a execution background.
 * <br>
 * It has many steps.
 * <br>
 * The task result also includes the result of each steps.
 */
public class PromiseTask extends PromiseResource
{
    private String name;
    private String description;
    private PromiseExecutionState state;
    private String createdByUri;
    private int expectedExcutionMs;
    private String createdTime;
    private String lastUpdatedTime;
    private String terminatedTime;
    private List<PromiseTaskStep> stepList;
    private List<String> subTaskUriList;
    private PromiseExecutionResult result;

    public PromiseTask()
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        createdTime = sdf.format(new Date());
        lastUpdatedTime = createdTime;
        expectedExcutionMs = 0;
        state = PromiseExecutionState.READY;
        stepList = new ArrayList<>();
        subTaskUriList = new ArrayList<>();
        result = new PromiseExecutionResult();
    }

    public PromiseTask(PromiseTask other)
    {
        PromiseResource.attributeCopy(this, other);
        this.setName(other.getName());
        this.setDescription(other.getDescription());
        this.setState(other.getState());
        this.setCreatedByUri(other.getCreatedByUri());
        this.setExpectedExcutionMs(other.getExpectedExcutionMs());
        this.setCreatedTime(other.getCreatedTime());
        this.setLastUpdatedTime(other.getLastUpdatedTime());
        this.setTerminatedTime(other.getTerminatedTime());
        this.setStepList(other.getStepList());
        this.setSubTaskUriList(other.getSubTaskUriList());
        this.setResult(other.getResult());
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

    public String getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(String createdTime)
    {
        this.createdTime = createdTime;
    }

    public String getLastUpdatedTime()
    {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime)
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getTerminatedTime()
    {
        return terminatedTime;
    }

    public void setTerminatedTime(String terminatedTime)
    {
        this.terminatedTime = terminatedTime;
    }

    public List<PromiseTaskStep> getStepList()
    {
        return stepList;
    }

    public void setStepList(List<PromiseTaskStep> stepList)
    {
        this.stepList = stepList;
    }

    public List<String> getSubTaskUriList()
    {
        return subTaskUriList;
    }

    public void setSubTaskUriList(List<String> subTaskUriList)
    {
        this.subTaskUriList = subTaskUriList;
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
