package com.promise.integrationtest.task;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.promise.common.PromiseExecutionResultState;
import com.promise.common.PromiseExecutionState;
import com.promise.integrationtest.PromisePublicInterfaceTest;
import com.promise.integrationtest.util.CommonTestUtil;
import com.promise.integrationtest.util.HttpJsonClient;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskResponse;

public class TaskTest extends PromisePublicInterfaceTest
{
    private static final PostTaskRequest postTaskRequest0;

    static
    {
        postTaskRequest0 = new PostTaskRequest();
        postTaskRequest0.setName("Task name");
    }

    public TaskTest()
            throws Exception
    {
        super();
    }

    @Test
    public void testCreateDefaultTask()
    {
        final ResponseEntity<PostTaskResponse> responseEntity = HttpJsonClient
                .post(HOSTNAME + "/rest/task", token, postTaskRequest0, PostTaskResponse.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        final PostTaskResponse postResponse = responseEntity.getBody();
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(postTaskRequest0.getName(), postResponse.getName());
        Assert.assertEquals(0, postResponse.getExpectedExcutionMs());
        Assert.assertEquals(PromiseExecutionState.READY, postResponse.getState());
        Assert.assertEquals(0, postResponse.getStepList().size());
        Assert.assertEquals(0, postResponse.getSubTaskUriList().size());
        Assert.assertEquals(PromiseExecutionResultState.UNKNOWN, postResponse.getResult().getState());
        Assert.assertEquals(0, postResponse.getResult().getReason().size());
        Assert.assertEquals(0, postResponse.getResult().getSolution().size());
    }
}
