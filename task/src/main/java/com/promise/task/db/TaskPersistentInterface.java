package com.promise.task.db;

import com.promise.task.Task;

public interface TaskPersistentInterface {

	public void saveTask(Task task);
	public Task getTask(String id);
	public void updateTask(String id, Task task);
	
}
