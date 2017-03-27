package com.promise.common.exception;

public class InternelErrorException extends PromiseException
{

    /**
     *
     */
    private static final long serialVersionUID = -1993521634515998110L;

    public InternelErrorException()
    {
        super();
        this.setDescription("The execution failed.");
        final String[] reason = {
                "An internel error happend."
        };
        final String[] solution = {
                "Contact support."
        };
        this.setReason(reason);
        this.setSolution(solution);
    }

}
