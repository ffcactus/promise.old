package com.promise.common.dto;

import org.springframework.http.HttpStatus;

import com.promise.common.constant.PromiseCategory;

public class PromiseNotFoundHttpOperationResponse extends PromiseHttpOperationResponse
{
    public PromiseNotFoundHttpOperationResponse(PromiseCategory category, String uri)
    {
        final PromiseOperationResponse response = new PromiseOperationResponse();
        response.setCategory(category);
        response.setUri(uri);
        response.setState(PromiseResponseState.ERROR);
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
