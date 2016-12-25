package com.promise.common;

import com.promise.common.exception.NoDbInstanceException;

public interface PromiseDaoInterface<C, G>
{
    public G create(C request);

    public G get(String id)
            throws NoDbInstanceException;

    public void delete(String id)
            throws NoDbInstanceException;
}
