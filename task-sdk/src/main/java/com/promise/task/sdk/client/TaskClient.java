package com.promise.task.sdk.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.promise.common.PromiseClient;
import com.promise.common.constant.PromiseCategory;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.UpdateTaskRequest;

public class TaskClient
{

    private final static Logger log = LoggerFactory.getLogger(TaskClient.class);

    public static ResponseEntity<?> createTask(CreateTaskRequest request)
    {
        final ResponseEntity<?> ret = PromiseClient.httpPost(
                PromiseClient.URL_HEAD + "/rest/task",
                request,
                PromiseClient.makeHeader(PromiseClient.getModuleToken(PromiseCategory.TASK.getValue()), null),
                GetTaskResponse.class);
        if (ret == null)
        {
            log.warn("Create task return null.");
        }
        else if (ret.getBody() instanceof GetTaskResponse)
        {
            final GetTaskResponse o = (GetTaskResponse) ret.getBody();
            log.info("Create task done, task name = " + o.getName());
        }
        else
        {
            log.warn("Create task return unexpected object");
        }
        return ret;
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
