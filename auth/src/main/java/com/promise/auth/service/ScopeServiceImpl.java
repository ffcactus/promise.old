package com.promise.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.dao.ScopeDaoInterface;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
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
    public CreateScopeResponse createScope(CreateScopeRequest createScopeRequest)
    {

        return scopeDao.createScope(createScopeRequest);
        //        CreateScopeResponse ret;
        //        ScopeEntity scopeDao = ScopeEntity.makeInstance(dto);
        //        scopeDao = scopeDao.createScope(scopeDao);
        //        ret = convertScopeDao(scopeDao);
        //        // AcessPoint type is the same in the request and response.
        //        ret.setAccessPointList(dto.getAccessPointList());
        //
        //        final ArrayList<String> accessPointIdList = new ArrayList<>();
        //        for (final PromiseAccessPoint each : dto.getAccessPointList())
        //        {
        //            accessPointIdList.add(scopeDao.createAccessPoint(convertAccessPointDto(each)).getId());
        //        }
        //        for (final String each : accessPointIdList)
        //        {
        //            scopeDao.bindAccessPointToScope(each, ret.getId());
        //        }
        //        return ret;
    }

    @Override
    public GetScopeResponse getScope(String id)
            throws NoDbInstanceException
    {
        return scopeDao.getScope(id);
        //        final ScopeEntity scopeDao = scopeDao.getScope(id);
        //        final List<AccessPointEntity> accessPointDaoList = scopeDao.getScopeAccessPointList(id);
        //        return convertToGetScopeResponse(scopeDao, accessPointDaoList);
    }

    @Override
    public void deleteScope(String id)
            throws NoDbInstanceException
    {
        scopeDao.deleteScope(id);
    }

    @Override
    public GetScopeListResponse getScopeList(Optional<Integer> start, Optional<Integer> count)
    {
        return scopeDao.getScopeList(
                start.isPresent() ? start.get() : 0,
                count.isPresent() ? count.get() : 0);
        //        final List<ScopeEntity> scopeDaoList = scopeDao.getScopeList(
        //                start.isPresent() ? start.get() : 0,
        //                count.isPresent() ? count.get() : 0);
        //        final GetScopeListResponse ret = new GetScopeListResponse();
        //
        //        // The start point should follow the start point in the request?
        //        ret.setStart(start.isPresent() ? start.get() : 0);
        //        ret.setCount(scopeDaoList.size());
        //        final List<GetScopeResponse> memberList = new ArrayList<>();
        //        for (final ScopeEntity each : scopeDaoList)
        //        {
        //            try
        //            {
        //                memberList.add(getScope(each.getId()));
        //            }
        //            catch (final NoDbInstanceException e)
        //            {
        //                // TODO
        //                System.out.println("Failed to get scope by ID.");
        //            }
        //        }
        //        ret.setMemberList(memberList);
        //        return ret;
    }

    @Override
    public void updateScope(String id, CreateScopeRequest dto)
    {
        // TODO Auto-generated method stub

    }

    //    private AccessPointEntity convertAccessPointDto(PromiseAccessPoint input)
    //    {
    //        final AccessPointEntity ret = new AccessPointEntity();
    //        ret.setType(input.getType());
    //        ret.setValue(input.getValue());
    //        return ret;
    //    }
    //
    //    private CreateScopeResponse convertScopeDao(ScopeEntity input)
    //    {
    //        final CreateScopeResponse ret = new CreateScopeResponse();
    //        PromiseResource.attributeCopy(ret, input);
    //        ret.setCategory(PromiseCategory.SCOPE);
    //        ret.setName(input.getName());
    //        ret.setDescription(input.getDescription());
    //        return ret;
    //    }
    //
    //    private GetScopeResponse convertToGetScopeResponse(ScopeEntity scopeDao, List<AccessPointEntity> accessPointDaoList)
    //    {
    //        final GetScopeResponse ret = new GetScopeResponse();
    //        PromiseResource.attributeCopy(ret, scopeDao);
    //        ret.setName(scopeDao.getName());
    //        ret.setDescription(scopeDao.getDescription());
    //        final List<PromiseAccessPoint> accessPointList = new ArrayList<>();
    //        for (final AccessPointEntity each : accessPointDaoList)
    //        {
    //            accessPointList.add(new PromiseAccessPoint(each.getType(), each.getValue()));
    //        }
    //        ret.setAccessPointList(accessPointList);
    //        return ret;
    //    }

}
