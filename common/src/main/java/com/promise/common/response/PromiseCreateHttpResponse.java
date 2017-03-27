package com.promise.common.response;

import org.springframework.http.HttpStatus;

public class PromiseCreateHttpResponse extends PromiseHttpResponse
{
    public PromiseCreateHttpResponse(String uri)
    {
        final PromiseOperationResponse response = new PromiseOperationResponse();
        response.setUri(uri);
        response.setState(PromiseResponseState.OK);
        setHttpStatus(HttpStatus.CREATED);
        setResponse(response);
    }
}
