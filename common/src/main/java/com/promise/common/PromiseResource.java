package com.promise.common;

import com.promise.common.constant.PromiseCategory;

/**
 * Represent the common object. These objects will contain URI and category
 * attributes.
 *
 */
public class PromiseResource
{

    private String id;
    private PromiseCategory category;
    private String uri;

    public PromiseResource()
    {

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
        return uri;
    }

    public PromiseCategory getCategory()
    {
        return category;
    }

    public void setCategory(PromiseCategory category)
    {
        this.category = category;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /**
     * Copy ID, category and URI.
     *
     * @param to
     * @param from
     */
    public static void attributeCopy(PromiseResource to, PromiseResource from)
    {
        to.id = from.id;
        to.category = from.category;
        to.uri = from.uri;
    }

    /**
     * Generate URI from category and ID.
     *
     * @param r
     */
    public static void makeUri(PromiseResource r)
    {
        r.uri = "/rest/" + r.category.getValue() + "/" + r.id;
    }

}
