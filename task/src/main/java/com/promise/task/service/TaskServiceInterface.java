package com.promise.task.service;

import com.promise.task.Task;

public interface TaskServiceInterface {

	public void postTask(Task task);

	public Task getTask(String id);

	public void updateTask(String id, Task task);

}
