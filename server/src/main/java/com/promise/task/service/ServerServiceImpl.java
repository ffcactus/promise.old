package com.promise.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.common.dto.PromiseHttpResponse;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.server.process.AddServerProcess;
import com.promise.task.sdk.client.TaskClient;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.AddServerResponse;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetServerResponse;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.PostTaskStepRequest;

@Service("taskService")
@Transactional
public class ServerServiceImpl implements ServerServiceInterface
{
    private final static PostTaskStepRequest ADD_SERVER_TASK_STEP[] = {
            new PostTaskStepRequest("Check connection", "Check the connection to the server.", 3 * 1000),
            new PostTaskStepRequest("Get basic information", "Get the basic information of the server.", 3 * 1000),
            new PostTaskStepRequest("Configure server", "Configure the server for the management.", 10 * 1000),
            new PostTaskStepRequest("Save server", "Save the server to the management platform.", 1 * 1000),
    };

    @Override
    public PromiseHttpResponse addServer(AddServerRequest request)
            throws InvalidRequestBodyException
    {

        final CreateTaskRequest taskRequest = new CreateTaskRequest();
        taskRequest.setName("Add Server");
        taskRequest.setDescription("Add a server to the management platform.");
        taskRequest.setExpectedExcutionMs(20 * 1000);
        taskRequest.setStepList(fromArray(ADD_SERVER_TASK_STEP));
        final ResponseEntity<?> createTaskResponseEntity = TaskClient.createTask(taskRequest);
        if (createTaskResponseEntity.getBody() instanceof GetTaskResponse)
        {
            if (createTaskResponseEntity.getStatusCode() == HttpStatus.CREATED)
            {
                final GetTaskResponse taskResponse = (GetTaskResponse) createTaskResponseEntity.getBody();
                final String taskUri = taskResponse.getUri();
                final Runnable process = new AddServerProcess(request, taskUri);
                process.run();
                final AddServerResponse ret = new AddServerResponse();
                ret.setTaskUri(taskUri);
                return new PromiseHttpResponse();
            }
            final PromiseHttpResponse ret = new PromiseHttpResponse();
            return ret;
        }
        else
        {
            System.out.println("Failed to create the task... Not combined token?");
            return null;
        }

    }

    @Override
    public GetServerResponse getServer(String id)
            throws NoDbInstanceException
    {
        return null;
    }

    @Override
    public PromiseHttpResponse removeServer(String id)
    {
        return null;
    }

    /**
     * Convert the PostTaskStepRequest from array to list.
     *
     * @param array
     * @return
     */
    private List<PostTaskStepRequest> fromArray(PostTaskStepRequest array[])
    {
        final List<PostTaskStepRequest> ret = new ArrayList<>();
        for (final PostTaskStepRequest each : array)
        {
            ret.add(each);
        }
        return ret;
    }
}
