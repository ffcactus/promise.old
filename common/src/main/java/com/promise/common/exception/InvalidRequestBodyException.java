package com.promise.common.exception;

import com.promise.common.constant.PromiseCategory;

public class InvalidRequestBodyException extends PromiseException
{
    /**
     *
     */
    private static final long serialVersionUID = -1793342163553781600L;

    public InvalidRequestBodyException(Exception e, PromiseCategory category)
    {
        super();
        this.setCategory(category);
        this.setName(category.name());
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
