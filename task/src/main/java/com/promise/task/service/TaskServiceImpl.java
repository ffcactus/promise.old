package com.promise.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.db.TaskEntity;
import com.promise.task.db.TaskDbInterface;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

@Component
@Scope("singleton")
public class TaskServiceImpl implements TaskServiceInterface
{

    @Autowired
    private TaskDbInterface taskDb;

    @Override
    public PostTaskResponse postTask(PostTaskRequest request)
    {
        final TaskEntity taskDao = TaskEntity.makeInstance(request);
        return new PostTaskResponse(TaskEntity.toPromiseTask(taskDb.postTask(taskDao)));
    }

    @Override
    public GetTaskResponse getTask(String id)
            throws NoDbInstanceException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateTaskResponse updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteTask(String id)
            throws NoDbInstanceException
    {
        // TODO Auto-generated method stub

    }

}
