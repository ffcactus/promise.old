package com.promise.task.exception;

import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.PromiseException;

public class TaskException extends PromiseException {

	public static final String NAME = "Task Exception";

	public final TaskException POST_WITH_ID = new TaskException(null, "Failed to post a task", null, null);

	public TaskException(Exception e, String description, String[] reason, String[] solution) {
		super(e);
		this.setCategory(PromiseCategory.TASK);
		this.setName(NAME);
		this.setDescription(description);
		this.setReason(reason != null ? reason : new String[0]);
		this.setSolution(solution != null ? solution : new String[0]);
	}

}
