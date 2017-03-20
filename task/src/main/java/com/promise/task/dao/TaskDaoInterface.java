package com.promise.task.dao;

import com.promise.common.PromiseDaoInterface;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskListResponse;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

public interface TaskDaoInterface extends PromiseDaoInterface<CreateTaskRequest, GetTaskResponse>
{
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
     * Get a list of task.
     *
     * @param start
     * @param count
     * @return
     */
    public GetTaskListResponse getTaskList(int start, int count);

}
