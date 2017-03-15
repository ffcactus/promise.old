package com.promise.server.process;

import com.promise.task.sdk.client.TaskClient;
import com.promise.task.sdk.dto.AddServerRequest;
import com.promise.task.sdk.dto.UpdateTaskRequest;

public class AddServerProcess implements Runnable
{
    private final String taskUrl;
    //    private final AddServerRequest request;

    public AddServerProcess(AddServerRequest request, String taskUrl)
    {
        //        this.request = request;
        this.taskUrl = taskUrl;
    }

    @Override
    public void run()
    {
        final UpdateTaskRequest updateRequest = new UpdateTaskRequest();
        TaskClient.updateTask(taskUrl, updateRequest);
        sleep(1000);

    }

    private void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (final InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
