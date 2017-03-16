package com.promise.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.service.AuthServiceInterface;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseErrorResponse;
import com.promise.common.PromiseToken;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.PromiseException;

@RestController
@RequestMapping("/rest/private")
public class AuthPrivateController
{

    @Autowired
    private AuthServiceInterface authService;

    private final Logger log = LoggerFactory.getLogger(AuthPrivateController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PromiseErrorResponse> exceptionHandler(HttpServletRequest req, Exception ex)
    {
        final PromiseErrorResponse response;

        if (ex instanceof PromiseException)
        {
            log.info("Handling PromiseException " + ((PromiseException) ex).getMessage());
            response = PromiseErrorResponse.makeInstance((PromiseException) ex);

        }
        else if (ex.getCause() instanceof PromiseException)
        {
            log.info("Handling PromiseException " + ((PromiseException) ex.getCause()).getMessage());
            response = PromiseErrorResponse.makeInstance((PromiseException) ex.getCause());
        }
        else
        {
            log.info("Handling unknown Exception " + ex.getStackTrace());
            response = PromiseErrorResponse.makeInstance(new InternelErrorException(PromiseCategory.AA));
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/auth")
    public ResponseEntity<PostAuthResponse> auth(@RequestHeader Map<String, String> header)
    {
        final PromiseToken token = PromiseClient.getToken(header);
        final PromiseAccessPoint accessPoint = PromiseClient.getAccessPoint(header);
        final PostAuthResponse response = authService.auth(token, accessPoint);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
