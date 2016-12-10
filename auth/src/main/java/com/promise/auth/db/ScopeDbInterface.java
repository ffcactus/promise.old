package com.promise.auth.db;

import java.util.List;

import com.promise.common.exception.NoDbInstanceException;

public interface ScopeDbInterface
{

    public ScopeDao createScope(ScopeDao scopeDao);

    public ScopeDao getScope(String id)
            throws NoDbInstanceException;

    public List<ScopeDao> getScopeList(int start, int count);

    public void deleteScope(String id)
            throws NoDbInstanceException;

    public ScopeDao updateScope(String id, ScopeDao scopeDao)
            throws NoDbInstanceException;

    public AccessPointDao createAccessPoint(AccessPointDao accessPoint);

    public List<AccessPointDao> getScopeAccessPointList(String scopeId);

    public void removeAccessPoint(String id)
            throws NoDbInstanceException;

    public void bindAccessPointToScope(String accessPointId, String scopeId);

    public void unbindAccessPointFromScope(String accessPointId, String scopeId);

}
