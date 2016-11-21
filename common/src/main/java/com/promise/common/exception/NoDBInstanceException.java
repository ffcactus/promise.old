package com.promise.common.exception;

import com.promise.common.constant.PromiseCategory;

public class NoDBInstanceException extends PromiseException
{

    /**
     *
     */
    private static final long serialVersionUID = 6875358611678749969L;

    public NoDBInstanceException(Exception e, PromiseCategory category)
    {
        super();
        this.setCategory(category);
        this.setName(category.name());
        this.setDescription("Failed to find the instance in database.");
        final String[] reason = {
                "Failed to find the instance in database."
        };
        final String[] solution = {
                "Make sure the instance is exist."
        };
        this.setReason(reason);
        this.setSolution(solution);
    }
}
