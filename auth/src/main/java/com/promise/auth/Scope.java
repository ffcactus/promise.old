package com.promise.auth;

import com.promise.common.PromiseResource;
import com.promise.common.constant.PromiseCategory;

/**
 * The object to represent the scope.
 *
 */
public class Scope extends PromiseResource
{

    public Scope(String id)
    {
        setId(id);
        setCategory(PromiseCategory.SCOPE);
    }

}
