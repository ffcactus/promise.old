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
    private static Map<String, TaskDao> db = new HashMap<>();

    @Override
    public TaskDao postTask(TaskDao task)
    {
        db.put(task.getId(), task);
        return task;
    }

    @Override
    public TaskDao getTask(String id)
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
    public TaskDao updateTask(String id, UpdateTaskRequest request)
            throws NoDbInstanceException
    {
        if (db.containsKey(id))
        {
            final TaskDao updated = TaskDao.updateFrom(db.get(id), request);
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
