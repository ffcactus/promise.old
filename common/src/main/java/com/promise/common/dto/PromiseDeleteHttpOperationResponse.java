package com.promise.common.dto;

import org.springframework.http.HttpStatus;

import com.promise.common.constant.PromiseCategory;

public class PromiseDeleteHttpOperationResponse extends PromiseHttpOperationResponse
{

    public PromiseDeleteHttpOperationResponse(PromiseCategory category, String uri)
    {
        final PromiseOperationResponse response = new PromiseOperationResponse();
        response.setCategory(category);
        response.setUri(uri);
        response.setState(PromiseResponseState.OK);
        setHttpStatus(HttpStatus.ACCEPTED);
        setResponse(response);
    }
}
