package com.promise.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.db.AccessPointDao;
import com.promise.auth.db.ScopeDao;
import com.promise.auth.db.ScopeDatabaseInterface;
import com.promise.auth.dto.AccessPoint;
import com.promise.auth.dto.CreateScopeRequest;
import com.promise.auth.dto.GetScopeResponse;

@Component
@Scope("singleton")
public class ScopeServiceImpl implements ScopeServiceInterface
{

    @Autowired
    private ScopeDatabaseInterface db;

    @Override
    public void createScope(CreateScopeRequest dto)
    {
        ScopeDao scopeDao = new ScopeDao();
        scopeDao.setName(dto.getName());
        String scopeId = db.createScope(scopeDao).getId();
        ArrayList<String> accessPointIdList = new ArrayList<String>();
        for (AccessPoint each : dto.getAccessPointList())
        {
            accessPointIdList.add(db.createAccessPoint(convertAccessPointDto(each)).getId());
        }
        for (String each : accessPointIdList)
        {
            db.bindAccessPointToScope(each, scopeId);
        }
    }

    @Override
    public GetScopeResponse getScope(String id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateScope(String id, CreateScopeRequest dto)
    {
        // TODO Auto-generated method stub

    }

    private AccessPointDao convertAccessPointDto(AccessPoint input)
    {
        AccessPointDao ret = new AccessPointDao();
        ret.setType(input.getType());
        ret.setValue(input.getValue());
        return ret;
    }
}
