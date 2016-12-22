package com.promise.task.dao;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

public interface TaskDaoInterface
{
    /**
     * Create a task.
     *
     * @param request The DTO that represents the task to create.
     * @return The DTO that represents the task created.
     */
    public PostTaskResponse postTask(PostTaskRequest request);

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
