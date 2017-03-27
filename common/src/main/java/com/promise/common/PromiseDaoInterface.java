package com.promise.common;

import java.util.List;

import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.common.response.PromiseHttpResponse;

/**
 * The common DAO interface.
 *
 * @param <C> The create request class.
 * @param <G> The get operation response.
 */
public interface PromiseDaoInterface<C, G>
{
    /**
     * Create the object in DB and return a response.
     *
     * @param request
     * @return
     * @throws DbOperationException
     */
    public PromiseHttpResponse create(C request)
            throws DbOperationException;

    /**
     * Get the object from DB, and generate response.
     *
     * @param id
     * @return
     * @throws NoDbInstanceException
     */
    public G get(String id);

    /**
     * Delete the object from DB and return the result.
     *
     * @param id
     * @return
     */
    public PromiseHttpResponse delete(String id);

    /**
     * Get the objects from DB.
     *
     * @param start
     * @param count
     * @return
     */
    public List<G> list(int start, int count);
}
