package com.promise.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.server.dao.ServerDaoInterface;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.GetServerResponse;

@Service("taskService")
@Transactional
public class ServerServiceImpl implements ServerServiceInterface
{

    @Autowired
    private ServerDaoInterface taskDao;

    @Override
    public GetServerResponse addServer(AddServerRequest request)
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
    public GetServerResponse getServer(String id)
            throws NoDbInstanceException
    {
        return taskDao.get(id);
    }

    @Override
    public void removeServer(String id)
            throws NoDbInstanceException
    {
        taskDao.delete(id);
    }

}
