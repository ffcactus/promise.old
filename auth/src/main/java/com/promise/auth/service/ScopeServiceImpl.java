package com.promise.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.db.AccessPointDao;
import com.promise.auth.db.ScopeDao;
import com.promise.auth.db.ScopeDatabaseInterface;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseResource;
import com.promise.common.constant.PromiseCategory;
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
        ScopeDao scopeDao = ScopeDao.makeInstance(dto);
        scopeDao = db.createScope(scopeDao);
        ret = convertScopeDao(scopeDao);
        // AcessPoint type is the same in the request and response.
        ret.setAccessPointList(dto.getAccessPointList());

        final ArrayList<String> accessPointIdList = new ArrayList<>();
        for (final PromiseAccessPoint each : dto.getAccessPointList())
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
    public void deleteScope(String id)
            throws NoDBInstanceException
    {
        db.deleteScope(id);
    }

    @Override
    public GetScopeListResponse getScopeList(int start, int count)
    {
        final List<ScopeDao> scopeDaoList = db.getScopeList(start, count);
        final GetScopeListResponse ret = new GetScopeListResponse();

        // The start point should follow the start point in the request?
        ret.setStart(start);
        ret.setCount(scopeDaoList.size());
        final List<GetScopeResponse> memberList = new ArrayList<>();
        for (final ScopeDao each : scopeDaoList)
        {
            try
            {
                memberList.add(getScope(each.getId()));
            }
            catch (final NoDBInstanceException e)
            {
                System.out.println("Failed to get scope by ID.");
            }
        }
        ret.setMemberList(memberList);
        return ret;
    }

    @Override
    public void updateScope(String id, CreateScopeRequest dto)
    {
        // TODO Auto-generated method stub

    }

    private AccessPointDao convertAccessPointDto(PromiseAccessPoint input)
    {
        final AccessPointDao ret = new AccessPointDao();
        ret.setType(input.getType());
        ret.setValue(input.getValue());
        return ret;
    }

    private CreateScopeResponse convertScopeDao(ScopeDao input)
    {
        final CreateScopeResponse ret = new CreateScopeResponse();
        PromiseResource.attributeCopy(ret, input);
        ret.setCategory(PromiseCategory.SCOPE);
        ret.setName(input.getName());
        ret.setDescription(input.getDescription());
        return ret;
    }

    private GetScopeResponse convertToGetScopeResponse(ScopeDao scopeDao, List<AccessPointDao> accessPointDaoList)
    {
        final GetScopeResponse ret = new GetScopeResponse();
        PromiseResource.attributeCopy(ret, scopeDao);
        ret.setName(scopeDao.getName());
        ret.setDescription(scopeDao.getDescription());
        final List<PromiseAccessPoint> accessPointList = new ArrayList<>();
        for (final AccessPointDao each : accessPointDaoList)
        {
            accessPointList.add(new PromiseAccessPoint(each.getType(), each.getValue()));
        }
        ret.setAccessPointList(accessPointList);
        return ret;
    }

}
