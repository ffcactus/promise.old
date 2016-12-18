package com.promise.task.db;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.promise.common.PromiseExecutionResult;
import com.promise.common.PromiseExecutionState;
import com.promise.common.PromiseResource;
import com.promise.common.PromiseTask;
import com.promise.common.PromiseTaskStep;
import com.promise.common.constant.PromiseCategory;
import com.promise.task.sdk.dto.PostTaskRequest;
import com.promise.task.sdk.dto.PostTaskStepRequest;
import com.promise.task.sdk.dto.UpdateTaskRequest;
import com.promise.util.PromiseUtil;

public class TaskEntity extends PromiseTask
{
    /**
     * Make an instance of TaskDao in which the ID, category and URI already
     * set.
     *
     * @return
     */
    public static TaskEntity makeInstance()
    {
        final TaskEntity ret = new TaskEntity();
        ret.setId(UUID.randomUUID().toString());
        ret.setCategory(PromiseCategory.TASK);
        PromiseResource.makeUri(ret);
        return ret;
    }

    public static TaskEntity makeInstance(PostTaskRequest dto)
    {
        // User should not add sub task directly.
        final TaskEntity ret = makeInstance();
        ret.setName(dto.getName());
        ret.setDescription(PromiseUtil.avoidNull(dto.getDescription()));
        ret.setExpectedExcutionMs(dto.getExpectedExcutionMs());
        if (dto.getStepList() != null)
        {
            final List<PromiseTaskStep> stepList = new ArrayList<>();
            for (final PostTaskStepRequest each : dto.getStepList())
            {
                final PromiseTaskStep step = new PromiseTaskStep();
                step.setName(each.getName());
                step.setDescription(each.getDescription());
                step.setState(PromiseExecutionState.READY);
                step.setExpectedExcutionMs(each.getExpectedExcutionMs());
                step.setResult(new PromiseExecutionResult());
                stepList.add(step);
            }
            ret.setStepList(stepList);
        }
        return ret;
    }

    public static PromiseTask toPromiseTask(TaskEntity dao)
    {
        return new PromiseTask(dao);
    }

    /**
     * Update a Task DAO with UpdateTaskRequest.
     *
     * @param current The current DAO.
     * @param request The update content.
     * @return The updated DAO.
     */
    public static TaskEntity updateFrom(TaskEntity current, UpdateTaskRequest request)
    {
        // TODO
        return current;
    }
}
