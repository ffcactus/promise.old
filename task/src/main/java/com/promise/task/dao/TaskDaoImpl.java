package com.promise.task.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.promise.common.PromiseExecutionResult;
import com.promise.common.PromiseExecutionResultState;
import com.promise.common.PromiseExecutionState;
import com.promise.common.PromiseTaskStep;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.entity.ExecutionResult;
import com.promise.task.entity.TaskEntity;
import com.promise.task.entity.TaskStep;
import com.promise.task.sdk.dto.GetTaskResponse;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskResponse;
import com.promise.task.sdk.dto.PostTaskStepRequest;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.task.sdk.dto.UpdateTaskResponse;
import com.promise.util.PromiseUtil;

@Repository("taskDao")
public class TaskDaoImpl implements TaskDaoInterface
{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public PostTaskResponse postTask(PostTaskRequest request)
    {
        final TaskEntity entity = new TaskEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCreatedByUri(request.getCreatedByUri());
        entity.setExpectedExcutionMs(request.getExpectedExcutionMs());

        // Set default value.
        entity.setCategory(PromiseCategory.TASK);
        entity.setState(PromiseExecutionState.READY);
        entity.setPercentage(0);
        final ExecutionResult preResult = new ExecutionResult();
        preResult.setState(PromiseExecutionResultState.UNKNOWN);
        entity.setResult(preResult);

        final List<TaskStep> stepList = new ArrayList<>();
        for (final PostTaskStepRequest each : PromiseUtil.emptyIfNull(request.getStepList()))
        {
            final TaskStep taskStep = new TaskStep();
            taskStep.setName(each.getName());
            taskStep.setDescription(each.getDescription());
            taskStep.setExpectedExcutionMs(each.getExpectedExcutionMs());

            // Set default value.
            taskStep.setState(PromiseExecutionState.READY);
            taskStep.setPercentage(0);
            final ExecutionResult preStepResult = new ExecutionResult();
            preStepResult.setState(PromiseExecutionResultState.UNKNOWN);
            taskStep.setResult(preStepResult);

            stepList.add(taskStep);
        }
        entity.setStepList(stepList);
        getSession().save(entity);
        getSession().flush();

        final PostTaskResponse response = new PostTaskResponse();
        response.setId(entity.getId().toString());
        response.setCategory(entity.getCategory());
        response.setUri(entity.getUri());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setState(entity.getState());
        response.setCreatedByUri(entity.getCreatedByUri());
        response.setExpectedExcutionMs(entity.getExpectedExcutionMs());
        response.setPercentage(entity.getPercentage());
        response.setCreatedTime(PromiseUtil.dateToString(entity.getCreatedTime()));
        response.setLastUpdatedTime(PromiseUtil.dateToString(entity.getLastUpdatedTime()));
        response.setTerminatedTime(PromiseUtil.dateToString(entity.getTerminatedTime()));
        final List<PromiseTaskStep> taskStepList = new ArrayList<>();
        for (final TaskStep each : PromiseUtil.emptyIfNull(entity.getStepList()))
        {
            final PromiseTaskStep taskStep = new PromiseTaskStep();
            taskStep.setName(each.getName());
            taskStep.setDescription(each.getDescription());
            taskStep.setState(each.getState());
            taskStep.setExpectedExcutionMs(each.getExpectedExcutionMs());
            taskStep.setPercentage(each.getPercentage());
            taskStep.setCreatedTime(each.getCreatedTime());
            taskStep.setLastUpdatedTime(each.getLastUpdatedTime());
            taskStep.setTerminatedTime(each.getTerminatedTime());
            final PromiseExecutionResult result = new PromiseExecutionResult();
            result.setState(each.getResult().getState());
            result.setDescription(each.getResult().getDescription());
            result.setReason(each.getResult().getReason());
            result.setSolution(each.getResult().getSolution());
            taskStep.setResult(result);

            taskStepList.add(taskStep);
        }
        response.setStepList(taskStepList);

        final List<String> subTaskUriList = new ArrayList<>();
        for (final TaskEntity each : PromiseUtil.emptyIfNull(entity.getSubTaskList()))
        {
            subTaskUriList.add(each.getUri());
        }
        response.setSubTaskUriList(subTaskUriList);
        return response;
    }

    @Override
    public GetTaskResponse getTask(String id)
            throws NoDbInstanceException
    {
        TaskEntity entity;
        try
        {
            entity = getSession().get(TaskEntity.class, UUID.fromString(id));
        }
        catch (final IllegalArgumentException e)
        {
            throw new NoDbInstanceException(PromiseCategory.TASK);
        }

        if (entity == null)
        {
            throw new NoDbInstanceException(PromiseCategory.TASK);
        }

        final GetTaskResponse response = new GetTaskResponse();
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setState(entity.getState());
        response.setCreatedByUri(entity.getCreatedByUri());
        response.setExpectedExcutionMs(entity.getExpectedExcutionMs());
        response.setPercentage(entity.getPercentage());
        response.setCreatedTime(PromiseUtil.dateToString(entity.getCreatedTime()));
        response.setLastUpdatedTime(PromiseUtil.dateToString(entity.getLastUpdatedTime()));
        response.setTerminatedTime(PromiseUtil.dateToString(entity.getTerminatedTime()));
        final List<PromiseTaskStep> taskStepList = new ArrayList<>();
        for (final TaskStep each : PromiseUtil.emptyIfNull(entity.getStepList()))
        {
            final PromiseTaskStep taskStep = new PromiseTaskStep();
            taskStep.setName(each.getName());
            taskStep.setDescription(each.getDescription());
            taskStep.setState(each.getState());
            taskStep.setExpectedExcutionMs(each.getExpectedExcutionMs());
            taskStep.setPercentage(each.getPercentage());
            taskStep.setCreatedTime(each.getCreatedTime());
            taskStep.setLastUpdatedTime(each.getLastUpdatedTime());
            taskStep.setTerminatedTime(each.getTerminatedTime());
            final PromiseExecutionResult result = new PromiseExecutionResult();
            result.setState(each.getResult().getState());
            result.setDescription(each.getResult().getDescription());
            result.setReason(each.getResult().getReason());
            result.setSolution(each.getResult().getSolution());
            taskStep.setResult(result);

            taskStepList.add(taskStep);
        }
        response.setStepList(taskStepList);

        final List<String> subTaskUriList = new ArrayList<>();
        for (final TaskEntity each : PromiseUtil.emptyIfNull(entity.getSubTaskList()))
        {
            subTaskUriList.add(each.getUri());
        }
        response.setSubTaskUriList(subTaskUriList);
        return response;
    }

    @Override
    public UpdateTaskResponse updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteTask(String id)
            throws NoDbInstanceException
    {
        TaskEntity entity;
        try
        {
            entity = getSession().get(TaskEntity.class, UUID.fromString(id));
        }
        catch (final IllegalArgumentException e)
        {
            throw new NoDbInstanceException(PromiseCategory.TASK);
        }

        if (entity == null)
        {
            throw new NoDbInstanceException(PromiseCategory.TASK);
        }
        getSession().delete(entity);
    }

}
