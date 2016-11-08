package com.promise.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.promise.task.Task;
import com.promise.task.db.TaskPersistentInterface;

@RestController
public class PostTaskController
{
	@Autowired
	private TaskPersistentInterface db;
    
    @PostMapping("/task")
    public void postTask(@RequestBody Task task) {
    	db.saveTask(task);
    }
}
