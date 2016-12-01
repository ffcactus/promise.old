package com.promise.auth.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Aspect
@Component
public class PromiseAuthAspect
{
    @Before("@annotation(com.promise.auth.aspect.PromisePublicInterface)")
    public void printAdvice(JoinPoint jp)
    {
        Object[] args = jp.getArgs();
        MethodSignature ms = (MethodSignature) jp.getSignature();
        Method m = ms.getMethod();

        Annotation[][] parameterAnnotations = m.getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            System.out.println("I am checking parameter: " + args[i]);
            for (Annotation annotation : annotations) {
                System.out.println(annotation);

                if (annotation.annotationType() == RequestBody.class) {
                    System.out.println("Found it");
                }
            }
        }
    }
}
