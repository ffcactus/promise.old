package com.promise.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.promise.common.response.PromiseExceptionHttpResponse;
import com.promise.common.response.PromiseOperationResponse;

public class PromiseExceptionController
{
    private final Logger log = LoggerFactory.getLogger(PromiseExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PromiseOperationResponse> exceptionHandler(HttpServletRequest req, Exception ex)
    {
        final PromiseExceptionHttpResponse response = new PromiseExceptionHttpResponse(req, ex);
        log.warn(
                "Response exception for request "
                        + req.getRequestURI()
                        + " response description = "
                        + response.getResponse().getDescription());
        return response.toResponseEntity();
    }
}
