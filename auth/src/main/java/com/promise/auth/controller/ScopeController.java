package com.promise.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.dto.CreateScopeRequest;
import com.promise.auth.dto.GetScopeResponse;
import com.promise.auth.service.ScopeServiceInterface;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InvalidRequestBodyException;

@RestController
@RequestMapping("/rest")
public class ScopeController
{

    @Autowired
    private ScopeServiceInterface service;

    @PostMapping("/scope")
    public void createScope(@RequestBody CreateScopeRequest scope)
            throws InvalidRequestBodyException
    {
        if (!scope.isValidRequest())
        {
            throw new InvalidRequestBodyException(null, PromiseCategory.SCOPE);
        }
        service.createScope(scope);
    }

    @GetMapping("/scope/{id}")
    public GetScopeResponse getScope(@PathVariable String id)
    {
        return service.getScope(id);
    }
}
