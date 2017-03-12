package com.promise.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.promise.common.PromiseDao;
import com.promise.common.constant.PromiseCategory;
import com.promise.server.entity.ServerEntity;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.GetServerListResponse;
import com.promise.task.sdk.dto.GetServerResponse;

@Repository("taskDao")
public class ServerDaoImpl extends PromiseDao<ServerEntity, AddServerRequest, GetServerResponse> implements ServerDaoInterface
{

    public ServerDaoImpl()
    {
        super(PromiseCategory.TASK);
    }

    @Override
    public GetServerListResponse getServerList(int start, int count)
    {
        final List<GetServerResponse> list = list(start, count);
        final GetServerListResponse ret = new GetServerListResponse();
        ret.setStart(start);
        ret.setCount(list.size());
        ret.setMemberList(list);
        return ret;
    }

    @Override
    public ServerEntity fromCreateRequestToEntity(AddServerRequest request)
    {
        return null;
    }

    @Override
    public GetServerResponse fromEntityToGetResponse(ServerEntity entity)
    {
        return null;

    }

}
