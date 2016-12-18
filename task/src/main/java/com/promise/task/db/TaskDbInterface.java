package com.promise.task.db;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.sdk.dto.UpdateTaskRequest;

public interface TaskDbInterface
{

    /**
     * Create a task.
     *
     * @param request The DAO that represents the task to create.
     * @return The DAO that represents the task created.
     */
    public TaskEntity postTask(TaskEntity task);

    /**
     * Get a task.
     *
     * @param id The ID of the task.
     * @return The DAO that represents the task get.
     * @throws NoDbInstanceException If the task can't be found by the ID.
     */
    public TaskEntity getTask(String id)
            throws NoDbInstanceException;

    /**
     * Update a task.
     *
     * @param id The ID of the task.
     * @param request The DAO that represents the task should be.
     * @return The DAO that represents the current task.
     * @throws NoDbInstanceException If the task can't be found by the ID.
     */
    public TaskEntity updateTask(String id, UpdateTaskRequest request)
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
