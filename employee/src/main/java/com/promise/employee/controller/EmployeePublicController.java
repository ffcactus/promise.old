package com.promise.employee.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.aspect.PromisePublicInterface;
import com.promise.common.PromiseErrorResponse;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.common.exception.PromiseException;
import com.promise.employee.dto.GetEmployeeResponse;
import com.promise.employee.dto.PostEmployeeRequest;
import com.promise.employee.dto.PostEmployeeResponse;
import com.promise.employee.service.EmployeeServiceInterface;

@RestController
@RequestMapping("/rest")
public class EmployeePublicController
{
    private final Logger log = Logger.getLogger(EmployeePublicController.class);

    @Autowired
    private EmployeeServiceInterface employeeService;

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
            response = PromiseErrorResponse.makeInstance(new InternelErrorException(PromiseCategory.USER));
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Post a employee.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the employee created.
     * @throws InvalidRequestBodyException If the request is invalid.
     */
    @PromisePublicInterface
    @PostMapping("/employee")
    public ResponseEntity<PostEmployeeResponse> postemployee(
            @RequestHeader Map<String, String> header,
            @RequestBody PostEmployeeRequest request)
            throws InvalidRequestBodyException
    {
        return new ResponseEntity<>(employeeService.postEmployee(request), HttpStatus.CREATED);
    }

    /**
     * Get a employee.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the employee to get.
     * @return The HTTP response that represents the employee.
     */
    @PromisePublicInterface
    @PostMapping("/employee/{id}")
    public ResponseEntity<GetEmployeeResponse> getemployee(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
