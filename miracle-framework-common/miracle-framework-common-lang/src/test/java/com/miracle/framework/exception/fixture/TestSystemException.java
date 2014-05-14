package com.miracle.framework.exception.fixture;

import com.miracle.framework.exception.SystemException;

public final class TestSystemException extends SystemException {
	
	private static final long serialVersionUID = 7500285468446033551L;
	
	public TestSystemException(final String errorMessage, final Object... args) {
		super(errorMessage, args);
	}
	
	public TestSystemException(final String errorMessage, final Exception cause, final Object... args) {
		super(errorMessage, cause, args);
	}
}