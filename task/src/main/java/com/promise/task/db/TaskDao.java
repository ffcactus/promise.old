package com.promise.task.db;

import java.util.UUID;

import com.promise.common.PromiseResource;
import com.promise.common.PromiseTask;
import com.promise.common.constant.PromiseCategory;
import com.promise.task.sdk.dto.UpdateTaskRequest;

public class TaskDao extends PromiseTask
{
    /**
     * Make an instance of TaskDao in which the ID, category and URI already
     * set.
     *
     * @return
     */
    public static TaskDao makeInstance()
    {
        final TaskDao ret = new TaskDao();
        ret.setId(UUID.randomUUID().toString());
        ret.setCategory(PromiseCategory.TASK);
        PromiseResource.makeUri(ret);
        return ret;
    }

    /**
     * Update a Task DAO with UpdateTaskRequest.
     *
     * @param current The current DAO.
     * @param request The update content.
     * @return The updated DAO.
     */
    public static TaskDao updateFrom(TaskDao current, UpdateTaskRequest request)
    {
        // TODO
        return current;
    }
}
