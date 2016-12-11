package com.promise.integrationtest.task;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.promise.common.PromiseExecutionResultState;
import com.promise.common.PromiseExecutionState;
import com.promise.common.PromiseTaskStep;
import com.promise.integrationtest.PromisePublicInterfaceTest;
import com.promise.integrationtest.util.CommonTestUtil;
import com.promise.integrationtest.util.HttpJsonClient;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskResponse;
import com.promise.task.sdk.dto.PostTaskStepRequest;

public class TaskTest extends PromisePublicInterfaceTest
{
    private static final PostTaskRequest postDefaultTaskRequest;
    private static final PostTaskRequest postFullTaskRequest;
    private static final PostTaskStepRequest step0;
    private static final PostTaskStepRequest step1;
    private static final List<PostTaskStepRequest> stepList;

    static
    {
        postDefaultTaskRequest = new PostTaskRequest();
        postDefaultTaskRequest.setName("Default task name");

        postFullTaskRequest = new PostTaskRequest();
        postFullTaskRequest.setName("Full task name");
        postFullTaskRequest.setDescription("Full task description");
        postFullTaskRequest.setExpectedExcutionMs(60 * 1000);

        step0 = new PostTaskStepRequest();
        step0.setName("Step 0");
        step0.setDescription("Step 0 description");
        step0.setExpectedExcutionMs(30 * 1000);

        step1 = new PostTaskStepRequest();
        step1.setName("Step 1");
        step1.setDescription("Step 1 description");
        step1.setExpectedExcutionMs(30 * 1000);

        stepList = new ArrayList<>();
        stepList.add(step0);
        stepList.add(step1);

        postFullTaskRequest.setStepList(stepList);
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
                .post(HOSTNAME + "/rest/task", token, postDefaultTaskRequest, PostTaskResponse.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        final PostTaskResponse postResponse = responseEntity.getBody();
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(postDefaultTaskRequest.getName(), postResponse.getName());
        Assert.assertEquals(0, postResponse.getExpectedExcutionMs());
        Assert.assertEquals(PromiseExecutionState.READY, postResponse.getState());
        Assert.assertNull(postResponse.getTerminatedTime());
        Assert.assertEquals(0, postResponse.getPercentage());
        Assert.assertEquals(0, postResponse.getStepList().size());
        Assert.assertEquals(0, postResponse.getSubTaskUriList().size());
        Assert.assertEquals(PromiseExecutionResultState.UNKNOWN, postResponse.getResult().getState());
        Assert.assertEquals(0, postResponse.getResult().getReason().size());
        Assert.assertEquals(0, postResponse.getResult().getSolution().size());
    }

    @Test
    public void testCreateFullTask()
    {
        final ResponseEntity<PostTaskResponse> responseEntity = HttpJsonClient
                .post(HOSTNAME + "/rest/task", token, postFullTaskRequest, PostTaskResponse.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        final PostTaskResponse postResponse = responseEntity.getBody();
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(postFullTaskRequest.getName(), postResponse.getName());
        Assert.assertEquals(postFullTaskRequest.getExpectedExcutionMs(), postResponse.getExpectedExcutionMs());
        Assert.assertEquals(PromiseExecutionState.READY, postResponse.getState());
        Assert.assertEquals(postFullTaskRequest.getStepList().size(), postResponse.getStepList().size());
        for (final PromiseTaskStep each : postResponse.getStepList())
        {
            Assert.assertEquals(PromiseExecutionState.READY, each.getState());
            Assert.assertEquals(0, each.getPercentage());
            Assert.assertNull(each.getCreatedTime());
            Assert.assertNull(each.getLastUpdatedTime());
            Assert.assertNull(each.getTerminatedTime());
            Assert.assertEquals(PromiseExecutionResultState.UNKNOWN, each.getResult().getState());
        }
        Assert.assertEquals(0, postResponse.getSubTaskUriList().size());
        Assert.assertEquals(PromiseExecutionResultState.UNKNOWN, postResponse.getResult().getState());
        Assert.assertEquals(0, postResponse.getResult().getReason().size());
        Assert.assertEquals(0, postResponse.getResult().getSolution().size());
    }
}
