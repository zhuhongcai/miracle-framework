package com.miracle.framework.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class LogicExceptionTest {
	
	@Test
	public void useDefaultConstructor() {
		assertNull(new TestLogicException().getMessage());
	}
	
	@Test
	public void useStringConstructor() {
		assertThat(new TestLogicException("error message").getMessage(), is("error message"));
	}
	
	private class TestLogicException extends LogicException {
		
		private static final long serialVersionUID = 7958467484863820692L;
		
		public TestLogicException() {
			super();
		}
		
		
		public TestLogicException(final String message) {
			super(message);
		}
	}
}
