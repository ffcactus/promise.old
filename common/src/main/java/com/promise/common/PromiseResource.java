package com.promise.common;

import com.promise.common.constant.PromiseCategory;

public class PromiseResource implements PromiseResourceInterface
{
    private String id;
    private String uri;
    private PromiseCategory category;

    public PromiseResource()
    {

    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String getUri()
    {
        return uri;
    }

    @Override
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    @Override
    public PromiseCategory getCategory()
    {
        return category;
    }

    @Override
    public void setCategory(PromiseCategory category)
    {
        this.category = category;
    }

    /**
     * Copy ID, category and URI.
     *
     * @param to
     * @param from
     */
    public static void attributeCopy(PromiseResourceInterface to, PromiseResourceInterface from)
    {
        to.setId(from.getId());
        to.setCategory(from.getCategory());
        to.setUri(from.getUri());
    }

    /**
     * Generate URI from category and ID.
     *
     * @param r
     */
    public static void makeUri(PromiseResourceInterface r)
    {
        r.setUri("/rest/" + r.getCategory().getValue() + "/" + r.getId());
    }

}
