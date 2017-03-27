package com.promise.server.service;

import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.common.response.PromiseHttpResponse;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.GetServerResponse;

public interface ServerServiceInterface
{

    /**
     * Add a server.
     *
     * @param request The DTO that represents the server to add.
     * @return The DTO that represents the result.
     * @throws Exception
     */
    public PromiseHttpResponse addServer(AddServerRequest request)
            throws InvalidRequestBodyException;

    /**
     * Get a server by ID.
     *
     * @param id The ID of the server.
     * @return The DTO that represents the server.
     * @throws NoDbInstanceException If the server can't be found by the ID.
     */
    public GetServerResponse getServer(String id)
            throws NoDbInstanceException;

    /**
     * Remove a server.
     *
     * @param id The ID of the server.
     * @throws NoDbInstanceException If the server can't be found by the ID.
     */
    public PromiseHttpResponse removeServer(String id);
}
