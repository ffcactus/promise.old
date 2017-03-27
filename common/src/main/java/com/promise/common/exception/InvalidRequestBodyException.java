package com.promise.common.exception;

public class InvalidRequestBodyException extends PromiseException
{
    /**
     *
     */
    private static final long serialVersionUID = -1793342163553781600L;

    public InvalidRequestBodyException(Exception e)
    {
        super();
        this.setDescription("Failed to process the request.");
        final String[] reason = {
                "The request body is invalid."
        };
        final String[] solution = {
                "Make sure the request body is valid."
        };
        this.setReason(reason);
        this.setSolution(solution);
    }

}
