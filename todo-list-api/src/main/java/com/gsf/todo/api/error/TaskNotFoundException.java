package com.gsf.todo.api.error;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3703405577462424465L;

	public TaskNotFoundException(Long id) {
        super("Task id not found : " + id);
    }

}
