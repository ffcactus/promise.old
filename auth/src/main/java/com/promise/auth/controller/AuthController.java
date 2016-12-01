package com.promise.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.aspect.PromisePublicInterface;
import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.auth.service.AuthServiceInterface;
import com.promise.auth.service.AuthServiceStatistic;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseErrorResponse;
import com.promise.common.PromiseToken;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.LoginFailureException;
import com.promise.common.exception.PromiseException;

@RestController
@RequestMapping("/rest")
public class AuthController
{

    @Autowired
    private AuthServiceInterface authService;

    @Autowired
    private AuthServiceStatistic statistic;

    private final Logger log = Logger.getLogger(AuthController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PromiseErrorResponse> exceptionHandler(HttpServletRequest req, Exception ex)
    {
        if (ex instanceof PromiseException)
        {
            log.info("Handling PromiseException " + ((PromiseException) ex).getMessage());
            final PromiseErrorResponse response = PromiseErrorResponse.makeInstance((PromiseException) ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
        {
            log.info("Handling unknown Exception " + ex.getStackTrace());
            final PromiseErrorResponse response = PromiseErrorResponse.makeInstance(new InternelErrorException(PromiseCategory.AA));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    @PromisePublicInterface
    public PostLoginResponse getLoginSession(@RequestBody PostLoginRequest request)
            throws InternelErrorException, LoginFailureException
    {
        statistic.recodeUri("/rest/login POST");
        return authService.login(request);
    }

    @PostMapping("/auth")
    public PostAuthResponse auth(@RequestHeader Map<String, String> header)
    {
        final PromiseToken token = PromiseClient.getToken(header);
        final PromiseAccessPoint accessPoint = PromiseClient.getAccessPoint(header);
        return authService.auth(token, accessPoint);
    }
}
