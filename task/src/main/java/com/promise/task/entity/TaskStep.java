package com.promise.task.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.promise.common.PromiseExecutionState;

@Entity(name = "taskstep")
@Table(name = "taskstep")
public class TaskStep
{
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private PromiseExecutionState state;

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "description", column = @Column(name = "execution_result_description")),
            @AttributeOverride(name = "state", column = @Column(name = "execution_result_state"))
    })
    private ExecutionResult result;

    public TaskStep()
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

    public ExecutionResult getResult()
    {
        return result;
    }

    public void setResult(ExecutionResult result)
    {
        this.result = result;
    }
}
