package com.promise.common.exception;

import com.promise.common.constant.PromiseCategory;

public class InvalidTokenException extends PromiseException
{

    /**
     *
     */
    private static final long serialVersionUID = -6814670988634767997L;

    public InvalidTokenException()
    {
        super();
        this.setCategory(PromiseCategory.AA);
        this.setName(PromiseCategory.AA.name());
        this.setDescription("Operation permitted.");
        final String[] reason = {
                "Missing token in the header.",
                "Invalied token."
        };
        final String[] solution = {
                "Add token in the header.",
                "Make sure the token in the header is valied."
        };
        this.setReason(reason);
        this.setSolution(solution);
    }

}
