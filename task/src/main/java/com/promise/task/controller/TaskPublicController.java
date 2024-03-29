package com.promise.task.controller;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.auth.sdk.aspect.PromisePublicInterface;
import com.promise.common.PromiseExceptionController;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.common.response.PromiseHttpResponse;
import com.promise.common.response.PromiseOperationResponse;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskListResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;
import com.promise.task.service.TaskServiceInterface;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class TaskPublicController extends PromiseExceptionController
{
    private final Logger log = LoggerFactory.getLogger(TaskPublicController.class);

    @Autowired
    private TaskServiceInterface taskService;

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
    public ResponseEntity<PromiseOperationResponse> postTask(
            @RequestHeader Map<String, String> header,
            @RequestBody CreateTaskRequest request)
            throws InvalidRequestBodyException
    {
        log.info("POST /task begin, task name = " + request.getName());
        final PromiseHttpResponse serviceRet = taskService.createTask(request);
        final ResponseEntity<PromiseOperationResponse> ret = new ResponseEntity<>(
                serviceRet.getResponse(),
                serviceRet.getHttpStatus());
        log.info("POST /task done, task name = " + request.getName() + " HTTP status = " + serviceRet.getHttpStatus());
        return ret;
    }

    /**
     * Get a task.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the task to get.
     * @return The HTTP response that represents the task.
     */
    @PromisePublicInterface
    @GetMapping("/task/{id}")
    public ResponseEntity<PromiseOperationResponse> getTask(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        return taskService.getTask(id).toResponseEntity();
    }

    /**
     * Get task list.
     *
     * @param header
     * @param start
     * @param count
     * @return
     * @throws InvalidRequestBodyException
     */
    @PromisePublicInterface
    @GetMapping("/task")
    ResponseEntity<GetTaskListResponse> getTaskList(
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
        final GetTaskListResponse ret = taskService.getTaskList(start, count);
        return new ResponseEntity<>(ret, HttpStatus.OK);
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
    public ResponseEntity<PromiseOperationResponse> deleteTask(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        final PromiseHttpResponse ret = taskService.deleteTask(id);
        return new ResponseEntity<>(ret.getResponse(), ret.getHttpStatus());
    }

}
