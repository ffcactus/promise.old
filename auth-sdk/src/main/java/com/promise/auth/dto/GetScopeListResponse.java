package com.promise.auth.dto;

import java.util.List;

public class GetScopeListResponse
{
    private int start;
    private int count;
    private List<GetScopeResponse> memberList;

    public GetScopeListResponse()
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

    public List<GetScopeResponse> getMemberList()
    {
        return memberList;
    }

    public void setMemberList(List<GetScopeResponse> memberList)
    {
        this.memberList = memberList;
    }
}
