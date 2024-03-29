package com.promise.auth.service;

import java.util.Optional;

import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.response.PromiseHttpResponse;

public interface ScopeServiceInterface
{

    /**
     * Create Scope.
     *
     * @param dto The object that represent the scope to create.
     * @return The object that represents the scope created.
     * @throws Exception
     */
    public PromiseHttpResponse createScope(CreateScopeRequest dto)
            throws InvalidRequestBodyException;

    /**
     * Get Scope by ID.
     *
     * @param id The id of the scope to return.
     * @return The object that represents the scope returned.
     */
    public PromiseHttpResponse getScope(String id);

    /**
     * Delete Scope by ID.
     *
     * @param id The id of the scope to delete.
     */
    public PromiseHttpResponse deleteScope(String id);

    /**
     * Get a certain number of scopes from the some point.
     *
     * @param start The start point to get.
     * @param count The count that is expected to return.
     * @return The object that represents the scope list returned.
     */
    public GetScopeListResponse getScopeList(Optional<Integer> start, Optional<Integer> count);

    public void updateScope(String id, CreateScopeRequest dto);
}
