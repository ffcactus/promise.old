package com.promise.server.dao;

import com.promise.common.PromiseDaoInterface;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.GetServerListResponse;
import com.promise.task.sdk.dto.GetServerResponse;

public interface ServerDaoInterface extends PromiseDaoInterface<AddServerRequest, GetServerResponse>
{

    /**
     * Get a list of server.
     *
     * @param start
     * @param count
     * @return
     */
    public GetServerListResponse getServerList(int start, int count);

}
