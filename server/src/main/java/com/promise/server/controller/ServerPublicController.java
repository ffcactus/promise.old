package com.promise.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.promise.server.service.ServerServiceInterface;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.GetServerResponse;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class ServerPublicController extends PromiseExceptionController
{

    @Autowired
    private ServerServiceInterface serverService;

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
    public ResponseEntity<PromiseOperationResponse> addServer(
            @RequestHeader Map<String, String> header,
            @RequestBody AddServerRequest request)
            throws InvalidRequestBodyException
    {
        final PromiseHttpResponse ret = serverService.addServer(request);
        return new ResponseEntity<>(ret.getResponse(), ret.getHttpStatus());
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
    public ResponseEntity<PromiseOperationResponse> deleteServer(
            @RequestHeader Map<String, String> header,
            @PathVariable String id)
    {
        final PromiseHttpResponse ret = serverService.removeServer(id);
        return new ResponseEntity<>(ret.getResponse(), ret.getHttpStatus());
    }

}
