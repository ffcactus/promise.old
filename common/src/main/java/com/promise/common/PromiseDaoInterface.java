package com.promise.common;

import java.util.List;

import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.NoDbInstanceException;

public interface PromiseDaoInterface<C, G>
{
    public G create(C request)
            throws DbOperationException;

    public G get(String id)
            throws NoDbInstanceException;

    public void delete(String id)
            throws NoDbInstanceException;

    public List<G> list(int start, int count);
}
