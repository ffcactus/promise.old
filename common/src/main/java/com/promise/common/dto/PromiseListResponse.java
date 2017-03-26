package com.promise.common.dto;

import java.util.List;

public class PromiseListResponse<T>
{
    private int start;
    private int count;
    private List<T> member;

    public PromiseListResponse()
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

    public List<T> getMember()
    {
        return member;
    }

    public void setMember(List<T> member)
    {
        this.member = member;
    }
}
