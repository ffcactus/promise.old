package com.promise.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.aspect.PromisePublicInterface;
import com.promise.auth.sdk.client.AuthClient;
import com.promise.auth.sdk.dto.CreateScopeRequest;
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
import com.promise.common.exception.NoDbInstanceException;
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

    /**
     * The exception handler for this controller.
     *
     * @param req The Servlet Request
     * @param ex The exception
     * @return The HTTP response that represents the exception.
     */
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

    /**
     * Create a user.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the user created.
     * @throws InvalidRequestBodyException If the request is invalid.
     * @throws NoSuchAlgorithmException
     * @throws NoDbInstanceException
     */
    @PromisePublicInterface
    @PostMapping("/user")
    ResponseEntity<CreateUserResponse> createUser(
            @RequestHeader Map<String, String> header,
            @RequestBody CreateUserRequest request)
            throws InvalidRequestBodyException, NoDbInstanceException, NoSuchAlgorithmException
    {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @PromisePublicInterface
    @GetMapping("/user")
    ResponseEntity<GetUserListResponse> getUserList(
            @RequestHeader Map<String, String> header,
            @PathVariable(value = "start") Optional<Integer> start,
            @PathVariable(value = "count") Optional<Integer> count)
            throws InvalidRequestBodyException
    {
        if ((start.isPresent() && start.get() < 0) || (count.isPresent() && count.get() < 0))
        {
            // TODO Invalid URL?
            throw new InvalidRequestBodyException(null, PromiseCategory.USER);
        }
        return new ResponseEntity<>(userService.getUserList(start, count), HttpStatus.OK);
    }

    /**
     * Get a user.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the user to get.
     * @return The HTTP response that represents the user.
     */
    @PromisePublicInterface
    @GetMapping("/user/{id}")
    ResponseEntity<GetUserResponse> getUser(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        AuthClient.aa(new PromiseToken("token"), new PromiseAccessPoint());
        try
        {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a user.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the user to delete.
     */
    @PromisePublicInterface
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            userService.deleteUser(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a scope.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the scope created.
     * @throws InvalidRequestBodyException If the request is invalid.
     */
    @PromisePublicInterface
    @PostMapping("/scope")
    public ResponseEntity<GetScopeResponse> createScope(
            @RequestHeader Map<String, String> header,
            @RequestBody CreateScopeRequest scope)
    {
        return new ResponseEntity<>(scopeService.createScope(scope), HttpStatus.CREATED);
    }

    @PromisePublicInterface
    @GetMapping("/scope")
    public ResponseEntity<GetScopeListResponse> getScopeList(
            @RequestHeader Map<String, String> header,
            @PathVariable(value = "start") Optional<Integer> start,
            @PathVariable(value = "count") Optional<Integer> count)
            throws InvalidRequestBodyException
    {
        if ((start.isPresent() && start.get() < 0) || (count.isPresent() && count.get() < 0))
        {
            // TODO Invalid URL?
            throw new InvalidRequestBodyException(null, PromiseCategory.SCOPE);
        }
        return new ResponseEntity<>(scopeService.getScopeList(start, count), HttpStatus.OK);
    }

    /**
     * Get a scope.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the scope to get.
     * @return The HTTP response that represents the scope.
     */
    @PromisePublicInterface
    @GetMapping("/scope/{id}")
    public ResponseEntity<GetScopeResponse> getScope(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(scopeService.getScope(id), HttpStatus.OK);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a scope.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the scope to delete.
     */
    @PromisePublicInterface
    @DeleteMapping("/scope/{id}")
    public ResponseEntity<String> deleteScope(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            scopeService.deleteScope(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
