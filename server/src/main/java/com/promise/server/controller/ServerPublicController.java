package com.promise.server.controller;

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
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.AddServerResponse;
import com.promise.task.sdk.dto.GetServerResponse;
import com.promise.task.service.ServerServiceInterface;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class ServerPublicController
{
    private final Logger log = Logger.getLogger(ServerPublicController.class);

    @Autowired
    private ServerServiceInterface serverService;

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
            response = PromiseErrorResponse.makeInstance(new InternelErrorException(PromiseCategory.SERVER));
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Add a server.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the adding server action.
     * @throws Exception
     */
    @PromisePublicInterface
    @PostMapping("/server")
    public ResponseEntity<AddServerResponse> addServer(
            @RequestHeader Map<String, String> header,
            @RequestBody AddServerRequest request)
            throws InvalidRequestBodyException
    {
        return new ResponseEntity<>(serverService.addServer(request), HttpStatus.CREATED);
    }

    /**
     * Get a server by ID.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the task to get.
     * @return The HTTP response that represents the task.
     */
    @PromisePublicInterface
    @PostMapping("/server/{id}")
    public ResponseEntity<GetServerResponse> getServer(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(serverService.getServer(id), HttpStatus.OK);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a server.
     *
     * @param header The header of the HTTP request.
     * @param id The ID of the server to delete.
     */
    @PromisePublicInterface
    @DeleteMapping("/server/{id}")
    public ResponseEntity<String> deleteServer(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        try
        {
            serverService.removeServer(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
        catch (final NoDbInstanceException e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
