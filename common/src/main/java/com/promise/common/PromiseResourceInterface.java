package com.promise.common;

import com.promise.common.constant.PromiseCategory;

/**
 *
 *
 */
public interface PromiseResourceInterface
{
    public String getId();

    public void setId(String id);

    public String getUri();

    public void setUri(String uri);

    public PromiseCategory getCategory();

    public void setCategory(PromiseCategory category);
    
}
