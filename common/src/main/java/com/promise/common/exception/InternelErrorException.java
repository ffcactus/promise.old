package com.promise.common.exception;

import com.promise.common.constant.PromiseCategory;

public class InternelErrorException extends PromiseException
{

    /**
     *
     */
    private static final long serialVersionUID = -1993521634515998110L;

    public InternelErrorException(PromiseCategory category)
    {
        super();
        this.setCategory(category);
        this.setName(category.name());
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
