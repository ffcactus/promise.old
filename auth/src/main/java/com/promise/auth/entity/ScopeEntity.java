package com.promise.auth.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.promise.common.PromiseEntity;
import com.promise.common.constant.PromiseCategory;

@Entity(name = "scope")
@Table(name = "scope")
public class ScopeEntity extends PromiseEntity
{

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @ElementCollection
    private List<AccessPoint> accessPointList;

    public ScopeEntity()
    {
        setCategory(PromiseCategory.SCOPE);
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

    public List<AccessPoint> getAccessPointList()
    {
        return accessPointList;
    }

    public void setAccessPointList(List<AccessPoint> accessPointList)
    {
        this.accessPointList = accessPointList;
    }

}
