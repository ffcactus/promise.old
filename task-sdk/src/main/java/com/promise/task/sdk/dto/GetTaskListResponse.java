package com.promise.task.sdk.dto;

import java.util.List;

public class GetTaskListResponse
{
    private int start;
    private int count;
    private List<GetTaskResponse> memberList;

    public GetTaskListResponse()
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

    public List<GetTaskResponse> getMemberList()
    {
        return memberList;
    }

    public void setMemberList(List<GetTaskResponse> memberList)
    {
        this.memberList = memberList;
    }

}
