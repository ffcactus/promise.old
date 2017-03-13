package com.promise.task.service;

import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.AddServerResponse;
import com.promise.task.sdk.dto.GetServerResponse;

public interface ServerServiceInterface
{

    /**
     * Add a server.
     *
     * @param request The DTO that represents the server to add.
     * @return The DTO that represents the server added.
     * @throws Exception
     */
    public AddServerResponse addServer(AddServerRequest request)
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
    public void removeServer(String id)
            throws NoDbInstanceException;
}
