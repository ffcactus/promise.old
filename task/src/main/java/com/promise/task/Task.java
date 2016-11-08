package com.promise.task;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Task
{
	private String id;
    private String name;

    public Task()
    {
        System.out.println("Task object created.");
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
