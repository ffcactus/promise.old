package com.promise.auth.sdk.dto;

import java.util.List;

public class GetUserListResponse
{
    private int start;
    private int count;
    private List<GetUserResponse> memberList;

    public GetUserListResponse()
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

    public List<GetUserResponse> getMemberList()
    {
        return memberList;
    }

    public void setMemberList(List<GetUserResponse> memberList)
    {
        this.memberList = memberList;
    }
}
