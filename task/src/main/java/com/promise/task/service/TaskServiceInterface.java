package com.promise.task.service;

import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

public interface TaskServiceInterface
{

    /**
     * Create a task.
     *
     * @param request The DTO that represents the task to create.
     * @return The DTO that represents the task created.
     * @throws Exception
     */
    public GetTaskResponse createTask(CreateTaskRequest request)
            throws InvalidRequestBodyException;

    /**
     * Get a task.
     *
     * @param id The ID of the task.
     * @return The DTO that represents the task get.
     * @throws NoDbInstanceException If the task can't be found by the ID.
     */
    public GetTaskResponse getTask(String id)
            throws NoDbInstanceException;

    /**
     * Update a task.
     *
     * @param id The ID of the task.
     * @param request The DTO that represents the task should be.
     * @return The DTO that represents the current task.
     * @throws NoDbInstanceException If the task can't be found by the ID.
     */
    public UpdateTaskResponse updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException;

    /**
     * Delete a task.
     *
     * @param id The ID of the task.
     * @throws NoDbInstanceException If the task can't be found by the ID.
     */
    public void deleteTask(String id)
            throws NoDbInstanceException;
}
