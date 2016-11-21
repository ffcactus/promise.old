package com.promise.common.exception;

import com.promise.common.constant.PromiseCategory;

public class LoginFailureException extends PromiseException
{

    /**
     *
     */
    private static final long serialVersionUID = 3046588682297467543L;

    public LoginFailureException()
    {
        super();
        this.setCategory(PromiseCategory.AA);
        this.setName(PromiseCategory.AA.name());
        this.setDescription("Login failure");
        final String[] reason = {
                "Invalied username or password."
        };
        final String[] solution = {
                "Make sure the username and password is valied."
        };
        this.setReason(reason);
        this.setSolution(solution);
    }

}
