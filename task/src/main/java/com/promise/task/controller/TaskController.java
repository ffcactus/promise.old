package com.promise.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.promise.task.Task;

@RestController
public class TaskController
{
    @Autowired
    private Task task;

    @GetMapping("/task")
    @ResponseBody
    String getTask()
    {
        task.setName("" + Math.random());
        return task.getName();
    }
}
