package com.promise.common.response;

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
