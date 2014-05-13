package com.miracle.framework.common.util.reflection;

import com.miracle.framework.common.core.exception.SystemException;

public class ReflectionException extends SystemException {
	
	private static final long serialVersionUID = -2643290924558203829L;
	private static final String MESSAGE = "Reflection fail, the reason is: %s";
	
	public ReflectionException(final String reason) {
		super(MESSAGE, reason);
	}
}
