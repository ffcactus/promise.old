package com.promise.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
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
        statistic.recodeUri("/rest/scope POST");
        if (!scope.isValidRequest())
        {
            throw new InvalidRequestBodyException(null, PromiseCategory.SCOPE);
        }
        return new ResponseEntity<>(service.createScope(scope), HttpStatus.CREATED);
    }

    @GetMapping("/scope")
    public ResponseEntity<GetScopeListResponse> getScopeList(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "count", defaultValue = "0") int count)
            throws InvalidRequestBodyException
    {
        statistic.recodeUri("/rest/scope GET");
        if (start < 0 || count < 0)
        {
            // TODO Invalid URL?
            throw new InvalidRequestBodyException(null, PromiseCategory.SCOPE);
        }
        return new ResponseEntity<>(service.getScopeList(start, count), HttpStatus.OK);
    }

    @GetMapping("/rest/scope/{id}")
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

    @DeleteMapping("/scope/{id}")
    public ResponseEntity<String> deleteScope(@PathVariable String id)
    {
        statistic.recodeUri("/rest/scope{id} DELETE");
        try
        {
            service.deleteScope(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        catch (final NoDBInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rest/scope/statistic")
    public ResponseEntity<AuthServiceStatistic> getStatistic()
    {
        statistic.recodeUri("/rest/scope/statistic GET");
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
