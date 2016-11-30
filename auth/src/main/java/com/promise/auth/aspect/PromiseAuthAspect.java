package com.promise.auth.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PromiseAuthAspect
{
    @Before("@annotation(com.promise.auth.aspect.PromisePublicInterface")
    public void printAdvice()
    {
        System.out.println("In printAdvice()");
    }
}
