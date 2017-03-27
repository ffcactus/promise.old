package com.promise.common.response;

import org.springframework.http.HttpStatus;

import com.promise.common.PromiseResource;

/**
 * The response that represents the get operation successfully returned,
 * The object is retrieved.
 *
 */
public class PromiseGetHttpResponse<T extends PromiseResource> extends PromiseHttpResponse
{
    public PromiseGetHttpResponse()
    {

    }

    public PromiseGetHttpResponse(T data)
    {
        final PromiseGetResponse<T> response = new PromiseGetResponse<>();
        response.setUri(data.getUri());
        response.setState(PromiseResponseState.OK);
        response.setData(data);
        setHttpStatus(HttpStatus.OK);
        setResponse(response);
    }

}
