package com.promise.auth.sdk.exception;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.PromiseException;

public class LoginFailException extends PromiseException
{

    /**
     * 
     */
    private static final long serialVersionUID = -7604613282412294923L;

    public LoginFailException(Exception e)
    {
        super(e);
        this.setCategory(PromiseCategory.AA);
        this.setName(PromiseCategory.AA.name());
        this.setDescription("Failed to login.");
        String[] reason = {
                "The username or password is incorrect.",
                "The user is not exist."
        };
        String[] solution = {
                "Make sure the username and password is correct",
                "Create the user."
        };
        this.setReason(reason);
        this.setSolution(solution);
    }

}
