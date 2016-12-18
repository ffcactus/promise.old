package com.promise.common;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.promise.common.constant.PromiseCategory;

public class PromiseEntity
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "uri")
    private String uri;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private PromiseCategory category;

    public PromiseEntity()
    {

    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public PromiseCategory getCategory()
    {
        return category;
    }

    public void setCategory(PromiseCategory category)
    {
        this.category = category;
    }

    public static void copyAttribute(PromiseResource resource, PromiseEntity entity)
    {
        resource.setId(entity.getId().toString());
        resource.setUri(entity.getUri());
        resource.setCategory(entity.getCategory());
    }

}