package com.promise.integrationtest.task;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.promise.common.PromiseExecutionResultState;
import com.promise.common.PromiseExecutionState;
import com.promise.common.PromiseTaskStep;
import com.promise.common.dto.PromiseOperationResponse;
import com.promise.integrationtest.PromisePublicInterfaceTest;
import com.promise.integrationtest.util.CommonTestUtil;
import com.promise.integrationtest.util.HttpJsonClient;
import com.promise.task.sdk.dto.CreateTaskRequest;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.PostTaskStepRequest;

public class TaskTest extends PromisePublicInterfaceTest
{
    private static final CreateTaskRequest postDefaultTaskRequest;
    private static final CreateTaskRequest postFullTaskRequest;
    private static final PostTaskStepRequest step0;
    private static final PostTaskStepRequest step1;
    private static final List<PostTaskStepRequest> stepList;

    static
    {
        postDefaultTaskRequest = new CreateTaskRequest();
        postDefaultTaskRequest.setName("Default task name");

        postFullTaskRequest = new CreateTaskRequest();
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
        final ResponseEntity<PromiseOperationResponse> responseEntity = HttpJsonClient
                .post(HOSTNAME + "/rest/task", token, postDefaultTaskRequest, PromiseOperationResponse.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        final PromiseOperationResponse postResponse = responseEntity.getBody();
        CommonTestUtil.assertPromiseOperationResponse(postResponse);

        // Clean up.
        final ResponseEntity<PromiseOperationResponse> deleteRet = HttpJsonClient
                .delete(HOSTNAME + postResponse.getUri(), token, PromiseOperationResponse.class);
        Assert.assertEquals(HttpStatus.ACCEPTED, deleteRet.getStatusCode());
    }

    @Test
    public void testCreateFullTask()
    {
        final ResponseEntity<PromiseOperationResponse> responseEntity = HttpJsonClient
                .post(HOSTNAME + "/rest/task", token, postFullTaskRequest, PromiseOperationResponse.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        final PromiseOperationResponse postResponse = responseEntity.getBody();
        CommonTestUtil.assertPromiseOperationResponse(postResponse);
    }

    @Test
    public void testDeleteNoneExistTask()
    {
        final ResponseEntity<PromiseOperationResponse> deleteRet = HttpJsonClient
                .delete(HOSTNAME + "/rest/task/" + "xxxx", token, PromiseOperationResponse.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, deleteRet.getStatusCode());
    }

    @Ignore
    public void testUpdateTask()
    {

    }

    @Test
    public void testGetTaskList()
    {
        final ResponseEntity<PromiseOperationResponse> responseEntity = HttpJsonClient
                .post(HOSTNAME + "/rest/task", token, postFullTaskRequest, PromiseOperationResponse.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        final PromiseOperationResponse postResponse = responseEntity.getBody();
        CommonTestUtil.assertPromiseOperationResponse(postResponse);

        final String taskUri = postResponse.getUri();
        final ResponseEntity<GetTaskResponse> getResponseEntity = HttpJsonClient
                .get(HOSTNAME + taskUri, token, GetTaskResponse.class);

        final GetTaskResponse getResponse = getResponseEntity.getBody();
        Assert.assertEquals(postFullTaskRequest.getName(), getResponse.getName());
        Assert.assertEquals(postFullTaskRequest.getExpectedExcutionMs(), getResponse.getExpectedExcutionMs());
        Assert.assertEquals(PromiseExecutionState.READY, getResponse.getState());
        Assert.assertEquals(postFullTaskRequest.getStepList().size(), getResponse.getStepList().size());
        for (final PromiseTaskStep each : getResponse.getStepList())
        {
            Assert.assertEquals(PromiseExecutionState.READY, each.getState());
            Assert.assertEquals(0, each.getPercentage());
            Assert.assertNull(each.getTerminatedTime());
            Assert.assertEquals(PromiseExecutionResultState.UNKNOWN, each.getResult().getState());
        }
        Assert.assertEquals(0, getResponse.getSubTaskUriList().size());
        Assert.assertEquals(PromiseExecutionResultState.UNKNOWN, getResponse.getResult().getState());
        Assert.assertEquals(0, getResponse.getResult().getReason().size());
        Assert.assertEquals(0, getResponse.getResult().getSolution().size());

        Assert.assertEquals(taskUri, getResponse.getUri());

        // Clean up.
        final ResponseEntity<PromiseOperationResponse> deleteRet = HttpJsonClient
                .delete(HOSTNAME + taskUri, token, PromiseOperationResponse.class);
        Assert.assertEquals(HttpStatus.ACCEPTED, deleteRet.getStatusCode());
    }
}
