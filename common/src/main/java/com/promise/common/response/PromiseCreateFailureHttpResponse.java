package com.promise.common.response;

import org.springframework.http.HttpStatus;

public class PromiseCreateFailureHttpResponse extends PromiseHttpResponse
{
    public PromiseCreateFailureHttpResponse(HttpStatus status, String reason[], String[] solution)
    {
        final PromiseOperationResponse response = new PromiseOperationResponse();
        response.setState(PromiseResponseState.ERROR);
        response.setDescription("Failed to create the object.");
        response.setReason(reason);
        response.setSolution(solution);
        setHttpStatus(status);
        setResponse(response);
    }
}
