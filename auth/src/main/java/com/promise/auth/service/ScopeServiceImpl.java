package com.promise.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.dao.ScopeDaoInterface;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.common.exception.NoDbInstanceException;

@Component
@Scope("singleton")
public class ScopeServiceImpl implements ScopeServiceInterface
{

    @Autowired
    private ScopeDaoInterface scopeDao;

    @Override
    public GetScopeResponse createScope(CreateScopeRequest createScopeRequest)
    {

        return scopeDao.create(createScopeRequest);
    }

    @Override
    public GetScopeResponse getScope(String id)
            throws NoDbInstanceException
    {
        return scopeDao.get(id);
    }

    @Override
    public void deleteScope(String id)
            throws NoDbInstanceException
    {
        scopeDao.delete(id);
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
