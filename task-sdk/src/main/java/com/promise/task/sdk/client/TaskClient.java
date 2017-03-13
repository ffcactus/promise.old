package com.promise.task.sdk.client;

import org.springframework.http.ResponseEntity;

import com.promise.common.PromiseClient;
import com.promise.common.constant.PromiseCategory;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;

public class TaskClient
{

    public static ResponseEntity<GetTaskResponse> createTask(CreateTaskRequest request)
    {
        return PromiseClient.httpPost(
                PromiseClient.URL_HEAD + "/rest/task",
                request,
                PromiseClient.makeHeader(PromiseClient.getModuleToken(PromiseCategory.TASK.getValue()), null),
                GetTaskResponse.class);
    }

    public static ResponseEntity<GetTaskResponse> updateTask(String url, UpdateTaskRequest request)
    {
        return PromiseClient.httpPut(
                PromiseClient.URL_HEAD + url,
                request,
                PromiseClient.makeHeader(PromiseClient.getModuleToken(PromiseCategory.TASK.getValue()), null),
                GetTaskResponse.class);
    }
}
