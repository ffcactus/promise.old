package com.promise.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.auth.dao.ScopeDaoInterface;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.response.PromiseGetHttpResponse;
import com.promise.common.response.PromiseHttpResponse;
import com.promise.common.response.PromiseNotFoundHttpResponse;

@Service("scopeService")
@Transactional
public class ScopeServiceImpl implements ScopeServiceInterface
{

    @Autowired
    private ScopeDaoInterface scopeDao;

    @Override
    public PromiseHttpResponse createScope(CreateScopeRequest createScopeRequest)
            throws InvalidRequestBodyException
    {
        try
        {
            return scopeDao.create(createScopeRequest);
        }
        catch (final DbOperationException e)
        {
            throw new InvalidRequestBodyException(e);
        }
    }

    @Override
    public PromiseHttpResponse getScope(String id)
    {
        final GetScopeResponse scope = scopeDao.get(id);
        if (scope == null)
        {
            return new PromiseNotFoundHttpResponse();
        }
        else
        {
            return new PromiseGetHttpResponse<>(scope);
        }
    }

    @Override
    public PromiseHttpResponse deleteScope(String id)
    {
        return scopeDao.delete(id);
    }

    @Override
    public GetScopeListResponse getScopeList(Optional<Integer> start, Optional<Integer> count)
    {
        return scopeDao.getScopeList(
                start.isPresent() ? start.get() : 0,
                count.isPresent() ? count.get() : 0);
    }

    @Override
    public void updateScope(String id, CreateScopeRequest dto)
    {
        // TODO Auto-generated method stub

    }
}
