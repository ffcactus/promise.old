package com.promise.task.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;
import com.promise.task.service.TaskServiceInterface;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class TaskPublicController
{
    private final Logger log = Logger.getLogger(TaskPublicController.class);

    @Autowired
    private TaskServiceInterface taskService;

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
            response = PromiseErrorResponse.makeInstance(new InternelErrorException(PromiseCategory.TASK));
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Post a task.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the task created.
     * @throws Exception
     */
    @PromisePublicInterface
    @PostMapping("/task")
    public ResponseEntity<GetTaskResponse> postTask(
            @RequestHeader Map<String, String> header,
            @RequestBody CreateTaskRequest request)
            throws InvalidRequestBodyException
    {
        return new ResponseEntity<>(taskService.createTask(request), HttpStatus.CREATED);
    }

    /**
     * Get a task.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the task to get.
     * @return The HTTP response that represents the task.
     */
    @PromisePublicInterface
    @PostMapping("/task/{id}")
    public ResponseEntity<GetTaskResponse> getTask(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update a task.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the task updated.
     */
    @PromisePublicInterface
    @PutMapping("/task/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(
            @RequestHeader Map<String, String> header,
            @PathVariable String id,
            @RequestBody UpdateTaskRequest request)
    {
        try
        {
            return new ResponseEntity<>(taskService.updateTask(id, request), HttpStatus.ACCEPTED);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a task.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the task to delete.
     */
    @PromisePublicInterface
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            taskService.deleteTask(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
