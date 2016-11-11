package com.promise.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.db.AccessPointDao;
import com.promise.auth.db.ScopeDao;
import com.promise.auth.db.ScopeDatabaseInterface;
import com.promise.auth.dto.AccessPoint;
import com.promise.auth.dto.CreateScopeRequest;
import com.promise.auth.dto.CreateScopeResponse;
import com.promise.auth.dto.GetScopeResponse;
import com.promise.common.exception.NoDBInstanceException;

@Component
@Scope("singleton")
public class ScopeServiceImpl implements ScopeServiceInterface
{

    @Autowired
    private ScopeDatabaseInterface db;

    @Override
    public CreateScopeResponse createScope(CreateScopeRequest dto)
    {
        CreateScopeResponse ret;
        ScopeDao scopeDao = new ScopeDao();
        scopeDao.setName(dto.getName());
        scopeDao.setDescription(dto.getDescription());
        scopeDao = db.createScope(scopeDao);
        ret = convertScopeDao(scopeDao);
        // AcessPoint type is the same in the request and response.
        ret.setAccessPointList(dto.getAccessPointList());

        final ArrayList<String> accessPointIdList = new ArrayList<>();
        for (final AccessPoint each : dto.getAccessPointList())
        {
            accessPointIdList.add(db.createAccessPoint(convertAccessPointDto(each)).getId());
        }
        for (final String each : accessPointIdList)
        {
            db.bindAccessPointToScope(each, ret.getId());
        }
        return ret;
    }

    @Override
    public GetScopeResponse getScope(String id)
            throws NoDBInstanceException
    {
        final ScopeDao scopeDao = db.getScope(id);
        final List<AccessPointDao> accessPointDaoList = db.getScopeAccessPointList(id);
        return convertToGetScopeResponse(scopeDao, accessPointDaoList);
    }

    @Override
    public void updateScope(String id, CreateScopeRequest dto)
    {
        // TODO Auto-generated method stub

    }

    private AccessPointDao convertAccessPointDto(AccessPoint input)
    {
        final AccessPointDao ret = new AccessPointDao();
        ret.setType(input.getType());
        ret.setValue(input.getValue());
        return ret;
    }

    private CreateScopeResponse convertScopeDao(ScopeDao input)
    {
        final CreateScopeResponse ret = new CreateScopeResponse();
        ret.setId(input.getId());
        ret.setName(input.getName());
        ret.setDescription(input.getDescription());
        return ret;
    }

    private GetScopeResponse convertToGetScopeResponse(ScopeDao scopeDao, List<AccessPointDao> accessPointDaoList)
    {
        final GetScopeResponse ret = new GetScopeResponse();
        ret.setId(scopeDao.getId());
        ret.setName(scopeDao.getName());
        ret.setDescription(scopeDao.getDescription());
        final List<AccessPoint> accessPointList = new ArrayList<>();
        for (final AccessPointDao each : accessPointDaoList)
        {
            accessPointList.add(new AccessPoint(each.getType(), each.getValue()));
        }
        ret.setAccessPointList(accessPointList);
        return ret;
    }

}