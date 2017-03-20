package com.promise.common.dto;

import org.springframework.http.HttpStatus;

public class PromiseHttpOperationResponse
{
    private HttpStatus httpStatus;
    private PromiseOperationResponse response;

    public PromiseHttpOperationResponse()
    {

    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus)
    {
        this.httpStatus = httpStatus;
    }

    public PromiseOperationResponse getResponse()
    {
        return response;
    }

    public void setResponse(PromiseOperationResponse response)
    {
        this.response = response;
    }

}
