package com.miracle.framework.reflection;

import com.miracle.framework.exception.SystemException;

public class ReflectionException extends SystemException {
	
	private static final long serialVersionUID = -2643290924558203829L;
	private static final String MESSAGE = "Reflection fail, the reason is: %s";
	
	public ReflectionException(final String reason) {
		super(MESSAGE, reason);
	}
}
