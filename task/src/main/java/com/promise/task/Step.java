package com.promise.task;

public class Step {

	private String name;
	private int estimatedTime;
	
	public Step() {
		
	}
	
	public Step(String name, int estimatedTime) {
		this.name = name;
		this.estimatedTime = estimatedTime;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getEstimatedTime() {
		return estimatedTime;
	}
	
	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}	
}
