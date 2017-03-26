package com.promise.common.dto;

import org.springframework.http.HttpStatus;

import com.promise.common.constant.PromiseCategory;

public class PromiseDeleteHttpResponse extends PromiseHttpResponse
{
    public PromiseDeleteHttpResponse(PromiseCategory category, String uri)
    {
        final PromiseOperationResponse response = new PromiseOperationResponse();
        response.setUri(uri);
        response.setState(PromiseResponseState.OK);
        setHttpStatus(HttpStatus.ACCEPTED);
        setResponse(response);
    }
}
