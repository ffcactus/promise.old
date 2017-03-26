package com.promise.common.dto;

public class PromiseGetResponse<T> extends PromiseOperationResponse
{
    private T data;

    public PromiseGetResponse()
    {

    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

}
