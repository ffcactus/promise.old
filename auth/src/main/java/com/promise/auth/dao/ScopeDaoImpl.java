package com.promise.auth.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.promise.auth.entity.AccessPoint;
import com.promise.auth.entity.ScopeEntity;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseDao;
import com.promise.common.constant.PromiseCategory;
import com.promise.util.PromiseUtil;

@Repository("scopeDao")
public class ScopeDaoImpl extends PromiseDao<ScopeEntity, CreateScopeRequest, GetScopeResponse> implements ScopeDaoInterface
{

    public ScopeDaoImpl()
    {
        super(ScopeEntity.class, PromiseCategory.SCOPE);
    }

    @Override
    public boolean isScopeExsit(String uri)
    {
        ScopeEntity entity;
        try
        {
            entity = getSession().get(ScopeEntity.class, PromiseUtil.getIdFromUri(uri));
        }
        catch (final IllegalArgumentException e)
        {
            return false;
        }
        return entity == null ? false : true;
    }

    @Override
    public GetScopeListResponse getScopeList(int start, int count)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ScopeEntity fromCreateRequestToEntity(CreateScopeRequest request)
    {
        final ScopeEntity entity = new ScopeEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        final List<AccessPoint> accessPointList = new ArrayList<>();
        for (final PromiseAccessPoint each : PromiseUtil.emptyIfNull(request.getAccessPointList()))
        {
            final AccessPoint accessPoint = new AccessPoint();
            accessPoint.setType(each.getType());
            accessPoint.setValue(each.getValue());
            accessPointList.add(accessPoint);
        }
        entity.setAccessPointList(accessPointList);
        return entity;
    }

    @Override
    public GetScopeResponse fromEntityToGetResponse(ScopeEntity entity)
    {
        final GetScopeResponse response = new GetScopeResponse();
        PromiseUtil.copyAttributeFromEntityToResource(response, entity);
        response.setName(entity.getName());
        final List<PromiseAccessPoint> accessPointList = new ArrayList<>();
        for (final AccessPoint each : entity.getAccessPointList())
        {
            final PromiseAccessPoint accessPoint = new PromiseAccessPoint();
            accessPoint.setType(each.getType());
            accessPoint.setValue(each.getValue());
            accessPointList.add(accessPoint);
        }
        response.setAccessPointList(accessPointList);
        return response;
    }

}
