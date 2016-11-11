package com.promise.auth.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.common.PromiseServiceStatistic;

@Component
@Scope("singleton")
public class AuthServiceStatistic extends PromiseServiceStatistic
{
    public AuthServiceStatistic()
    {
        super();
        setServiceName("Authentication and authorization service statistic.");
    }

}
