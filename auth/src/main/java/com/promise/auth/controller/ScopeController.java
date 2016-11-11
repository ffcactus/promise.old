package com.promise.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.dto.CreateScopeRequest;
import com.promise.auth.dto.CreateScopeResponse;
import com.promise.auth.dto.GetScopeResponse;
import com.promise.auth.service.AuthServiceStatistic;
import com.promise.auth.service.ScopeServiceInterface;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDBInstanceException;

@RestController
@RequestMapping("/rest")
public class ScopeController
{

    @Autowired
    private ScopeServiceInterface service;

    @Autowired
    private AuthServiceStatistic statistic;

    @PostMapping("/scope")
    public ResponseEntity<CreateScopeResponse> createScope(@RequestBody CreateScopeRequest scope)
            throws InvalidRequestBodyException
    {
        statistic.recodeUri("/scope POST");
        if (!scope.isValidRequest())
        {
            throw new InvalidRequestBodyException(null, PromiseCategory.SCOPE);
        }
        return new ResponseEntity<>(service.createScope(scope), HttpStatus.CREATED);
    }

    @GetMapping("/scope/{id}")
    public ResponseEntity<GetScopeResponse> getScope(@PathVariable String id)
    {
        statistic.recodeUri("/scope{id} GET");
        try
        {
            return new ResponseEntity<>(service.getScope(id), HttpStatus.OK);
        }
        catch (final NoDBInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/scope/statistic")
    public ResponseEntity<AuthServiceStatistic> getStatistic()
    {
        statistic.recodeUri("/scope/statistic GET");
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
