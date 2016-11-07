package com.promise.task.db;

import com.promise.task.Task;

public interface DatabaseInterface {

	public Task getTask(String id);
	public void setTask(Task task);
	
}
