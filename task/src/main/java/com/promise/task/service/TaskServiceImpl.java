package com.promise.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.dto.PromiseHttpOperationResponse;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.dao.TaskDaoInterface;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskServiceInterface
{

    @Autowired
    private TaskDaoInterface taskDao;

    //@Autowired
    //private TestServiceInterface testDao;

    @Override
    public PromiseHttpOperationResponse createTask(CreateTaskRequest request)
            throws InvalidRequestBodyException
    {
        try
        {
            return taskDao.create(request);
        }
        catch (final DbOperationException e)
        {
            throw new InvalidRequestBodyException(e, PromiseCategory.TASK);
        }
    }

    @Override
    public GetTaskResponse getTask(String id)
            throws NoDbInstanceException
    {
        return taskDao.get(id);
    }

    @Override
    public UpdateTaskResponse updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException
    {
        //return taskDao.updateTask(id, request);
        return null;
    }

    @Override
    public void deleteTask(String id)
            throws NoDbInstanceException
    {
        //taskDao.delete(id);
    }

}
