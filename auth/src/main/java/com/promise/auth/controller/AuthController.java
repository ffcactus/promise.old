package com.promise.auth.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.auth.service.AuthServiceStatistic;
import com.promise.auth.service.AuthenticationServiceInterface;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseToken;
import com.promise.common.exception.InternelErrorException;

@RestController
@RequestMapping("/rest")
public class AuthController
{
	
	@Autowired
	private AuthenticationServiceInterface authService;
	
    @Autowired
    private AuthServiceStatistic statistic;
	
	
    private final Logger log = Logger.getLogger(AuthController.class);

    @PostMapping("/login")
    public PostLoginResponse getLoginSession(@RequestBody PostLoginRequest request)
            throws InternelErrorException
    {
    	statistic.recodeUri("/rest/login POST");
        return authService.login(request);
    }

    @PostMapping("/auth")
    public PostAuthResponse auth(@RequestHeader Map<String, String> header)
    {
        PromiseToken token = PromiseClient.getToken(header);
        PromiseAccessPoint accessPoint = PromiseClient.getAccessPoint(header);
        return authService.auth(token, accessPoint);        
    }
}
