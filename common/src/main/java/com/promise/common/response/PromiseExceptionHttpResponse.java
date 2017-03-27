package com.promise.common.response;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.promise.common.exception.PromiseException;

public class PromiseExceptionHttpResponse extends PromiseHttpResponse
{
    public PromiseExceptionHttpResponse(HttpServletRequest req, Exception ex)
    {
        PromiseException pe = null;
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        if (ex instanceof PromiseException)
        {
            pe = (PromiseException) ex;
        }
        else if (ex.getCause() instanceof PromiseException)
        {
            pe = (PromiseException) ex.getCause();
        }
        final PromiseOperationResponse response = new PromiseOperationResponse();
        if (pe == null)
        {
            final String reason[] = {
                    "Unknown reason."
            };
            final String solution[] = {
                    "Contact support."
            };
            response.setDescription("Internal error.");
            response.setReason(reason);
            response.setSolution(solution);
        }
        else
        {
            response.setDescription(pe.getDescription());
            response.setReason(pe.getReason());
            response.setSolution(pe.getSolution());
            response.setState(PromiseResponseState.ERROR);
        }
        setResponse(response);
    }
}
