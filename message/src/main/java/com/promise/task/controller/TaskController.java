package com.promise.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.promise.task.Task;

@RestController
public class TaskController
{
    @GetMapping("/task")
    @ResponseBody
    Task getTask()
    {
        return new Task("my task.");
    }
}
