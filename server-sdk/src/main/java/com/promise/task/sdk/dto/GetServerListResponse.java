package com.promise.task.sdk.dto;

import java.util.List;

public class GetServerListResponse
{
    private int start;
    private int count;
    private List<GetServerResponse> memberList;

    public GetServerListResponse()
    {

    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public List<GetServerResponse> getMemberList()
    {
        return memberList;
    }

    public void setMemberList(List<GetServerResponse> memberList)
    {
        this.memberList = memberList;
    }

}
