package com.promise.task.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.promise.common.PromiseEntity;
import com.promise.common.PromiseExecutionState;

@Entity(name = "promise-task")
@Table(name = "promise-task")
public class TaskEntity extends PromiseEntity
{

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private PromiseExecutionState state;

    @Column(name = "createdByUri")
    private String createdByUri;

    @Column(name = "expectedExcutionMs")
    private int expectedExcutionMs;

    @Column(name = "percentage")
    private int percentage;

    @Column(name = "createdTime")
    @CreationTimestamp
    private Date createdTime;

    @Column(name = "lastUpdatedTime")
    @UpdateTimestamp
    private Date lastUpdatedTime;

    @Column(name = "terminatedTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date terminatedTime;

    @ElementCollection
    private List<TaskStep> stepList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> subTaskList;

    @Embedded
    private ExecutionResult result;

    public TaskEntity()
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

    public List<TaskStep> getStepList()
    {
        return stepList;
    }

    public void setStepList(List<TaskStep> stepList)
    {
        this.stepList = stepList;
    }

    public List<TaskEntity> getSubTaskList()
    {
        return subTaskList;
    }

    public void setSubTaskList(List<TaskEntity> subTaskList)
    {
        this.subTaskList = subTaskList;
    }

    public ExecutionResult getResult()
    {
        return result;
    }

    public void setResult(ExecutionResult result)
    {
        this.result = result;
    }

}
