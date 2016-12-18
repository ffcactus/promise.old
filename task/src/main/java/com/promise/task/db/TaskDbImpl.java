package com.promise.task.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.NoDbInstanceException;
import com.promise.task.sdk.dto.UpdateTaskRequest;

@Component
@Scope("singleton")
public class TaskDbImpl implements TaskDbInterface
{
    private static Map<String, TaskEntity> db = new HashMap<>();

    @Override
    public TaskEntity postTask(TaskEntity task)
    {
        db.put(task.getId(), task);
        return task;
    }

    @Override
    public TaskEntity getTask(String id)
            throws NoDbInstanceException
    {
        if (db.containsKey(id))
        {
            return db.get(id);
        }
        else
        {
            throw new NoDbInstanceException(PromiseCategory.TASK);
        }
    }

    @Override
    public TaskEntity updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException
    {
        if (db.containsKey(id))
        {
            final TaskEntity updated = TaskEntity.updateFrom(db.get(id), request);
            db.put(id, updated);
            return updated;
        }
        else
        {
            throw new NoDbInstanceException(PromiseCategory.TASK);
        }
    }

    @Override
    public void deleteTask(String id)
            throws NoDbInstanceException
    {
        if (db.containsKey(id))
        {
            db.remove(id);
        }
        else
        {
            throw new NoDbInstanceException(PromiseCategory.TASK);
        }

    }

}
