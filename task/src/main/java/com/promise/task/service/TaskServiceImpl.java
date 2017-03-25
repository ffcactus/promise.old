package com.promise.task.service;

import java.util.Optional;

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
import com.promise.task.sdk.dto.GetTaskListResponse;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskServiceInterface
{

    @Autowired
    private TaskDaoInterface taskDao;

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
        final GetTaskResponse ret = taskDao.get(id);
        return ret;
    }

    @Override
    public UpdateTaskResponse updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException
    {
        //return taskDao.updateTask(id, request);
        return null;
    }

    @Override
    public PromiseHttpOperationResponse deleteTask(String id)
    {
        return taskDao.delete(id);
    }

    @Override
    public GetTaskListResponse getTaskList(Optional<Integer> start, Optional<Integer> count)
    {
        final GetTaskListResponse ret = taskDao.getTaskList(
                start.isPresent() ? start.get() : 0,
                count.isPresent() ? count.get() : 0);
        return ret;
    }

}
