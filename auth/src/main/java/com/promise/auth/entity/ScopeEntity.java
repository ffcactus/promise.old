package com.promise.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.promise.common.PromiseEntity;

@Entity(name = "promise_scope")
public class ScopeEntity extends PromiseEntity
{

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "description")
    private String description;

    public ScopeEntity()
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

}
