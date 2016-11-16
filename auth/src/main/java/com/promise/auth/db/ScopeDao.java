package com.promise.auth.db;

import java.util.UUID;

import com.promise.auth.dto.CreateScopeRequest;
import com.promise.common.PromiseResource;
import com.promise.common.constant.PromiseCategory;

public class ScopeDao extends PromiseResource
{
    private String name;
    private String description;

    public ScopeDao()
    {

    }

    /**
     * Make an instance of ScopeDao in which the ID, category and URI already
     * set.
     *
     * @return
     */
    public static ScopeDao makeInstance()
    {
        final ScopeDao ret = new ScopeDao();
        ret.setId(UUID.randomUUID().toString());
        ret.setCategory(PromiseCategory.SCOPE);
        PromiseResource.makeUri(ret);
        return ret;
    }

    /**
     * Make an instance of ScopeDao from DTO object.
     *
     * @param dto
     * @return
     */
    public static ScopeDao makeInstance(CreateScopeRequest dto)
    {
        final ScopeDao ret = makeInstance();
        ret.setName(dto.getName());
        ret.setDescription(dto.getDescription());
        return ret;
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
