package com.promise.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.aspect.PromisePublicInterface;
import com.promise.auth.sdk.client.AuthClient;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.GetUserResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.auth.service.AuthServiceInterface;
import com.promise.auth.service.ScopeServiceInterface;
import com.promise.auth.service.UserServiceInterface;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseErrorResponse;
import com.promise.common.PromiseToken;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.LoginFailureException;
import com.promise.common.exception.NoDBInstanceException;
import com.promise.common.exception.PromiseException;

@RestController
@RequestMapping("/rest")
public class AuthPublicController
{

    @Autowired
    private AuthServiceInterface authService;

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private ScopeServiceInterface scopeService;

    private final Logger log = Logger.getLogger(AuthPublicController.class);

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

    @PostMapping("/login")
    public PostLoginResponse getLoginSession(@RequestBody PostLoginRequest request)
            throws InternelErrorException, LoginFailureException
    {
        return authService.login(request);
    }

    /*
     * User controller goes here.
     */
    @PromisePublicInterface
    @PostMapping("/user")
    ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest dto)
            throws InvalidRequestBodyException
    {
        if (!dto.isValidRequest())
        {
            throw new InvalidRequestBodyException(null, PromiseCategory.USER);
        }
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    @PromisePublicInterface
    @GetMapping("/user")
    ResponseEntity<GetUserListResponse> getUserList(
            @RequestHeader Map<String, String> header,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "count", defaultValue = "0") int count)
            throws InvalidRequestBodyException
    {
        if (start < 0 || count < 0)
        {
            // TODO Invalid URL?
            throw new InvalidRequestBodyException(null, PromiseCategory.USER);
        }
        return new ResponseEntity<>(userService.getUserList(start, count), HttpStatus.OK);
    }

    @PromisePublicInterface
    @GetMapping("/user/{id}")
    GetUserResponse getUser(@PathVariable String id)
    {
        AuthClient.aa(new PromiseToken("token"), new PromiseAccessPoint());
        return null;
    }

    /*
     * Scope controller goes here.
     */
    @PromisePublicInterface
    @PostMapping("/scope")
    public ResponseEntity<CreateScopeResponse> createScope(@RequestBody CreateScopeRequest scope)
            throws InvalidRequestBodyException
    {
        log.info("HTTP POST /rest/scope " + scope.toDebugString());
        if (!scope.isValidRequest())
        {
            throw new InvalidRequestBodyException(null, PromiseCategory.SCOPE);
        }
        return new ResponseEntity<>(scopeService.createScope(scope), HttpStatus.CREATED);
    }

    @PromisePublicInterface
    @GetMapping("/scope")
    public ResponseEntity<GetScopeListResponse> getScopeList(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "count", defaultValue = "0") int count)
            throws InvalidRequestBodyException
    {
        log.info("HTTP GET /rest/scope");
        if (start < 0 || count < 0)
        {
            // TODO Invalid URL?
            throw new InvalidRequestBodyException(null, PromiseCategory.SCOPE);
        }
        return new ResponseEntity<>(scopeService.getScopeList(start, count), HttpStatus.OK);
    }

    @PromisePublicInterface
    @GetMapping("/scope/{id}")
    public ResponseEntity<GetScopeResponse> getScope(@PathVariable String id)
    {
        log.info("HTTP GET /rest/scope/" + id);
        try
        {
            return new ResponseEntity<>(scopeService.getScope(id), HttpStatus.OK);
        }
        catch (final NoDBInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PromisePublicInterface
    @DeleteMapping("/scope/{id}")
    public ResponseEntity<String> deleteScope(@PathVariable String id)
    {
        log.info("HTTP DELETE /rest/scope/" + id);
        try
        {
            scopeService.deleteScope(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        catch (final NoDBInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
