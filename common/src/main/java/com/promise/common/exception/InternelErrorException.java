package com.promise.common.exception;

import com.promise.common.constant.PromiseCategory;

public class InternelErrorException extends PromiseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1993521634515998110L;

	public InternelErrorException(Exception e, PromiseCategory category) {
		super(e);
		this.setCategory(category);
		this.setName(category.name());
		this.setDescription("The execution failed.");
		String[] reason = {"An internel error happend."};
		String[] solution = {"Contact support."};
		this.setReason(reason);
		this.setSolution(solution);
	}
	
}
