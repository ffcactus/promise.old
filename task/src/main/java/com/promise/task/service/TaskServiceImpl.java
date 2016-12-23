package com.promise.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.dao.TaskDaoInterface;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskServiceInterface
{

    @Autowired
    private TaskDaoInterface taskDao;

    @Override
    public PostTaskResponse postTask(PostTaskRequest request)
    {
        return taskDao.postTask(request);
    }

    @Override
    public GetTaskResponse getTask(String id)
            throws NoDbInstanceException
    {
        return taskDao.getTask(id);
    }

    @Override
    public UpdateTaskResponse updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException
    {
        return taskDao.updateTask(id, request);
    }

    @Override
    public void deleteTask(String id)
            throws NoDbInstanceException
    {
        taskDao.deleteTask(id);
    }

}
