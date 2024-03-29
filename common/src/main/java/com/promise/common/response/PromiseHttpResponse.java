package com.promise.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PromiseHttpResponse
{
    private HttpStatus httpStatus;
    private PromiseOperationResponse response;

    public PromiseHttpResponse()
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

    public ResponseEntity<PromiseOperationResponse> toResponseEntity()
    {
        final HttpStatus status = getHttpStatus();
        final PromiseOperationResponse operationResponse = getResponse();
        return new ResponseEntity<>(operationResponse, status);
    }

}
