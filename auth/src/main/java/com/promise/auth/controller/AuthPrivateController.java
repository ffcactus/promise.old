package com.promise.auth.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.dto.PostAuthResponse;
import com.promise.auth.service.AuthServiceInterface;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseClient;
import com.promise.common.PromiseExceptionController;
import com.promise.common.PromiseToken;

@RestController
@RequestMapping("/rest/private")
public class AuthPrivateController extends PromiseExceptionController
{

    @Autowired
    private AuthServiceInterface authService;

    private final Logger log = LoggerFactory.getLogger(AuthPrivateController.class);

    @PostMapping("/auth")
    public ResponseEntity<PostAuthResponse> auth(@RequestHeader Map<String, String> header)
    {
        log.info("POST /auth begin");
        final PromiseToken token = PromiseClient.getToken(header);
        final PromiseAccessPoint accessPoint = PromiseClient.getAccessPoint(header);
        final PostAuthResponse response = authService.auth(token, accessPoint);
        log.info("POST /auth done, authenticated = " + response.isAuthenticated() + " authorized = " + response.isAuthorized());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
