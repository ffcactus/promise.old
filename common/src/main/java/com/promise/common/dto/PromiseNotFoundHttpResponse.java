package com.promise.common.dto;

import org.springframework.http.HttpStatus;

public class PromiseNotFoundHttpResponse extends PromiseHttpResponse
{
    public PromiseNotFoundHttpResponse()
    {
        final PromiseOperationResponse response = new PromiseOperationResponse();
        response.setState(PromiseResponseState.WARN);
        response.setName("Not found.");
        response.setDescription("Can't find the object.");
        final String reason[] = {
                "The URI doesn't exist."
        };
        final String solution[] = {
                "Provide the right URI."
        };
        response.setReason(reason);
        response.setSolution(solution);

        setHttpStatus(HttpStatus.NOT_FOUND);
        setResponse(response);
    }
}
