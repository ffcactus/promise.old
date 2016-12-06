package com.promise.auth.sdk.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The public interface of promise module.
 * The public interface means it can be called from outside, for example, from
 * the user client.
 * The public interface will do authentication.
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PromisePublicInterface
{

}
