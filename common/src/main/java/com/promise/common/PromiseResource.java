package com.promise.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.constant.PromiseConstant;

/**
 * Represent the common object. These objects will contain URI and category
 * attributes.
 *
 */
public class PromiseResource
{

    private String id;
    @JsonIgnore
    private PromiseCategory category;

    public PromiseResource()
    {

    }

    public PromiseResource(String id, PromiseCategory category)
    {
        this.id = id;
        this.category = category;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUri()
    {
        return PromiseConstant.makeUri(category, id);
    }

    public PromiseCategory getCategory()
    {
        return category;
    }

    public void setCategory(PromiseCategory category)
    {
        this.category = category;
    }

}
