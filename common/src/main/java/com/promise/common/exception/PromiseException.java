package com.promise.exception;

import com.promise.constant.PromiseCategory;

public class PromiseException {

	private Exception e;
	private PromiseCategory category;
	private String name;
	private String description;
	private String[] reason;
	private String[] solution;

	/**
	 * The default construct that encapsulate the standard Java exception.
	 * 
	 * @param e
	 */
	public PromiseException(Exception e) {
		this.e = e;
	}

	/**
	 * Get the raw Java exception.
	 * @return The raw Java exception.
	 */
	public Exception getE() {
		return e;
	}

	public PromiseCategory getCategory() {
		return category;
	}

	public void setCategory(PromiseCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getReason() {
		return reason;
	}

	public void setReason(String[] reason) {
		this.reason = reason;
	}

	public String[] getSolution() {
		return solution;
	}

	public void setSolution(String[] solution) {
		this.solution = solution;
	}

}
