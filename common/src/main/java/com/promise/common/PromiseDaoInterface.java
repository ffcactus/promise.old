package com.promise.common;

import java.util.List;

import com.promise.common.dto.PromiseHttpOperationResponse;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.NoDbInstanceException;

/**
 * The common DAO interface.
 *
 * @param <C> The create request class.
 * @param <G> The get operation response.
 */
public interface PromiseDaoInterface<C, G>
{
    public PromiseHttpOperationResponse create(C request)
            throws DbOperationException;

    public G get(String id)
            throws NoDbInstanceException;

    public PromiseHttpOperationResponse delete(String id);

    public List<G> list(int start, int count);
}
