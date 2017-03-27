package com.promise.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.aspect.PromisePublicInterface;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetUserListResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.auth.service.AuthServiceInterface;
import com.promise.auth.service.ScopeServiceInterface;
import com.promise.auth.service.UserServiceInterface;
import com.promise.common.PromiseExceptionController;
import com.promise.common.exception.DbOperationException;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.LoginFailureException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.common.response.PromiseHttpResponse;
import com.promise.common.response.PromiseOperationResponse;

@RestController
@CrossOrigin
@RequestMapping("/rest")
public class AuthPublicController extends PromiseExceptionController
{

    @Autowired
    private AuthServiceInterface authService;

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private ScopeServiceInterface scopeService;

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
    ResponseEntity<PromiseOperationResponse> createUser(
            @RequestHeader Map<String, String> header,
            @RequestBody CreateUserRequest request)
            throws InvalidRequestBodyException
    {
        final PromiseHttpResponse serviceRet = userService.createUser(request);
        return new ResponseEntity<>(serviceRet.getResponse(), serviceRet.getHttpStatus());
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
            throw new InvalidRequestBodyException(null);
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
    ResponseEntity<PromiseOperationResponse> getUser(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        return userService.getUser(id).toResponseEntity();
    }

    /**
     * Delete a user.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the user to delete.
     */
    @PromisePublicInterface
    @DeleteMapping("/user/{id}")
    public ResponseEntity<PromiseOperationResponse> deleteUser(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        final PromiseHttpResponse serviceRet = userService.deleteUser(id);
        return new ResponseEntity<>(serviceRet.getResponse(), serviceRet.getHttpStatus());
    }

    /**
     * Create a scope.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the scope created.
     * @throws DbOperationException
     * @throws InvalidRequestBodyException If the request is invalid.
     */
    @PromisePublicInterface
    @PostMapping("/scope")
    public ResponseEntity<PromiseOperationResponse> createScope(
            @RequestHeader Map<String, String> header,
            @RequestBody CreateScopeRequest scope)
            throws InvalidRequestBodyException
    {
        final PromiseHttpResponse serviceRet = scopeService.createScope(scope);
        return new ResponseEntity<>(serviceRet.getResponse(), serviceRet.getHttpStatus());
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
            throw new InvalidRequestBodyException(null);
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
    public ResponseEntity<PromiseOperationResponse> getScope(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        return scopeService.getScope(id).toResponseEntity();
    }

    /**
     * Delete a scope.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the scope to delete.
     */
    @PromisePublicInterface
    @DeleteMapping("/scope/{id}")
    public ResponseEntity<PromiseOperationResponse> deleteScope(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        final PromiseHttpResponse ret = scopeService.deleteScope(id);
        return new ResponseEntity<>(ret.getResponse(), ret.getHttpStatus());
    }

}
