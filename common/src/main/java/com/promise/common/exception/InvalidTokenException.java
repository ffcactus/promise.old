package com.promise.common.exception;

public class InvalidTokenException extends PromiseException
{

    /**
     *
     */
    private static final long serialVersionUID = -6814670988634767997L;

    public InvalidTokenException()
    {
        super();
        this.setDescription("Operation not permitted.");
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
