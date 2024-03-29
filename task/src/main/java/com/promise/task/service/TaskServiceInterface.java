package com.promise.task.service;

import java.util.Optional;

import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.common.response.PromiseHttpResponse;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskListResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

public interface TaskServiceInterface
{

    /**
     * Create a task.
     *
     * @param request The DTO that represents the task to create.
     * @return The DTO that represents the response.
     * @throws Exception
     */
    public PromiseHttpResponse createTask(CreateTaskRequest request)
            throws InvalidRequestBodyException;

    /**
     * Get a task.
     *
     * @param id The ID of the task.
     * @return The DTO that represents the task get.
     */
    public PromiseHttpResponse getTask(String id);

    /**
     * Get task list.
     *
     * @param start
     * @param count
     * @return
     */
    public GetTaskListResponse getTaskList(Optional<Integer> start, Optional<Integer> count);

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
     */
    public PromiseHttpResponse deleteTask(String id);
}
