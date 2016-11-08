package com.promise.task.service;

import com.promise.task.Task;

public class TaskService implements TaskServiceInterface {

	@Override
	public void postTask(Task task) {
		if(task.getId() != null) {
			
		}

	}

	@Override
	public Task getTask(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTask(String id, Task task) {
		// TODO Auto-generated method stub

	}

}
