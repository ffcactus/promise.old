package com.promise.task;

public class Task {
	private final String id;
	private final String name;

	public Task(String name) {
		this.id = name;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
