package com.promise.task.dao;

import org.springframework.stereotype.Repository;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

@Repository("TaskDao")
public class TaskDaoImpl implements TaskDaoInterface
{

    @Override
    public PostTaskResponse postTask(PostTaskRequest request)
    {
        // TODO Auto-generated method stub
        return null;
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
