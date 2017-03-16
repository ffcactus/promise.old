package com.promise.auth.sdk.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.promise.auth.sdk.client.AuthClient;
import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseToken;
import com.promise.common.exception.InvalidTokenException;

@Aspect
@Component
public class PromiseAuthAspect
{

    private final Logger log = LoggerFactory.getLogger(PromiseAuthAspect.class);

    @Before("@annotation(com.promise.auth.sdk.aspect.PromisePublicInterface)")
    public void promisePublicInterfaceAspect(JoinPoint jp)
            throws InvalidTokenException
    {
        final Object[] args = jp.getArgs();
        final MethodSignature ms = (MethodSignature) jp.getSignature();
        final Method m = ms.getMethod();

        final Annotation[][] parameterAnnotations = m.getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++)
        {
            final Annotation[] annotations = parameterAnnotations[i];
            for (final Annotation annotation : annotations)
            {
                System.out.println(annotation);

                if (annotation.annotationType() == RequestHeader.class)
                {
                    @SuppressWarnings("unchecked")
                    final Map<String, String> header = (Map<String, String>) args[i];
                    final PromiseToken token = PromiseClient.getToken(header);
                    final PromiseAccessPoint accessPoint = PromiseClient.getAccessPoint(header);
                    final ResponseEntity<PostAuthResponse> response = AuthClient.aa(token, accessPoint);
                    if (response.getStatusCode() == HttpStatus.OK)
                    {
                        if (response.getBody().isAuthenticated() && response.getBody().isAuthorized())
                        {
                            return;
                        }
                        else
                        {
                            log.warn("Throwing InvalidTokenException authenticated = "
                                    +
                                    response.getBody().isAuthenticated()
                                    +
                                    " authorized = "
                                    +
                                    response.getBody().isAuthorized());
                            throw new InvalidTokenException();
                        }
                    }
                    else
                    {
                        log.warn("Throwing InvalidTokenException response status code = " + response.getStatusCode());
                        throw new InvalidTokenException();
                    }
                }
            }
        }
        // Can't find the header.
        throw new InvalidTokenException();
    }

}
