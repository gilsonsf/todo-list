package com.gsf.todo.api.error;

import java.util.Set;

public class TaskUnSupportedFieldPatchException extends RuntimeException {

	private static final long serialVersionUID = -3238557669580494749L;

	public TaskUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
