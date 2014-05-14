package com.miracle.framework.exception.fixture;

import com.miracle.framework.exception.LogicException;

public final class TestLogicException extends LogicException {
	
	private static final long serialVersionUID = 7958467484863820692L;
	
	public TestLogicException() {
		super();
	}
	
	public TestLogicException(final String message) {
		super(message);
	}
}
