package com.promise.auth.service;

import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.common.exception.NoDBInstanceException;

public interface ScopeServiceInterface
{

    /**
     * Create Scope.
     *
     * @param dto The object that represent the scope to create.
     * @return The object that represents the scope created.
     */
    public CreateScopeResponse createScope(CreateScopeRequest dto);

    /**
     * Get Scope by ID.
     *
     * @param id The id of the scope to return.
     * @return The object that represents the scope returned.
     * @throws NoDBInstanceException If the scope is unable to find.
     */
    public GetScopeResponse getScope(String id)
            throws NoDBInstanceException;

    /**
     * Delete Scope by ID.
     *
     * @param id THe id of the scope to delete.
     * @throws NoDBInstanceException If the scope is unable to find.
     */
    public void deleteScope(String id)
            throws NoDBInstanceException;

    /**
     * Get a certain number of scopes from the some point.
     *
     * @param start The start point to get.
     * @param count The count that is expected to return.
     * @return The object that represents the scope list returned.
     */
    public GetScopeListResponse getScopeList(int start, int count);

    public void updateScope(String id, CreateScopeRequest dto);
}
