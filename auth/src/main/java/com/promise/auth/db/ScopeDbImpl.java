package com.promise.auth.db;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.NoDbInstanceException;

@Component
@Scope("singleton")
public class ScopeDbImpl implements ScopeDbInterface
{

    private static Map<String, ScopeDao> scopeDB = new HashMap<>();
    private static Map<String, AccessPointDao> accessPointDB = new HashMap<>();
    private static Map<String, SimpleEntry<String, String>> bindingDB = new HashMap<>();

    @Override
    public ScopeDao createScope(ScopeDao scopeDao)
    {
        scopeDB.put(scopeDao.getId(), scopeDao);
        return scopeDao;
    }

    @Override
    public ScopeDao getScope(String id)
            throws NoDbInstanceException
    {
        if (scopeDB.containsKey(id))
        {
            return scopeDB.get(id);
        }
        else
        {
            throw new NoDbInstanceException(PromiseCategory.SCOPE);
        }
    }

    @Override
    public List<ScopeDao> getScopeList(int start, int count)
    {
        final List<ScopeDao> ret = new ArrayList<>(scopeDB.values());
        return ret;
    }

    @Override
    public void deleteScope(String id)
            throws NoDbInstanceException
    {
        if (scopeDB.containsKey(id))
        {
            scopeDB.remove(id);
        }
        else
        {
            throw new NoDbInstanceException(PromiseCategory.SCOPE);
        }
    }

    @Override
    public ScopeDao updateScope(String id, ScopeDao scopeDao)
            throws NoDbInstanceException
    {
        if (scopeDB.containsKey(id))
        {
            return scopeDB.put(id, scopeDao);
        }
        else
        {
            throw new NoDbInstanceException(PromiseCategory.SCOPE);
        }
    }

    @Override
    public AccessPointDao createAccessPoint(AccessPointDao accessPoint)
    {
        accessPointDB.put(accessPoint.getId(), accessPoint);
        return accessPoint;
    }

    @Override
    public List<AccessPointDao> getScopeAccessPointList(String scopeId)
    {
        final List<AccessPointDao> ret = new ArrayList<>();
        for (final AbstractMap.SimpleEntry<String, String> each : bindingDB.values())
        {
            if (each.getValue().equals(scopeId))
            {
                ret.add(accessPointDB.get(each.getKey()));
            }
        }
        return ret;
    }

    @Override
    public void removeAccessPoint(String id)
            throws NoDbInstanceException
    {
        if (accessPointDB.containsKey(id))
        {
            accessPointDB.remove(id);
        }
        else
        {
            throw new NoDbInstanceException(PromiseCategory.SCOPE);
        }
    }

    @Override
    public void bindAccessPointToScope(String accessPointId, String scopeId)
    {
        final String id = UUID.randomUUID().toString();
        final AbstractMap.SimpleEntry<String, String> entry = new AbstractMap.SimpleEntry<>(accessPointId, scopeId);
        bindingDB.put(id, entry);

    }

    @Override
    public void unbindAccessPointFromScope(String accessPointId, String scopeId)
    {
        for (final String id : bindingDB.keySet())
        {
            final AbstractMap.SimpleEntry<String, String> c = bindingDB.get(id);
            if (c.getKey().equals(accessPointId) && c.getValue().equals(scopeId))
            {
                bindingDB.remove(id);
            }
        }
    }

}