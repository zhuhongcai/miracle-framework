package com.miracle.framework.common.core.exception;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class SystemExceptionTest {
	
	@Test
	public void useConstructorWithoutCause() {
		TestSystemException systemException = new TestSystemException("error message is: '%s'", "details...");
		assertThat(systemException.getMessage(), is("error message is: 'details...'"));
		assertNull(systemException.getCause());
	}
	
	@Test
	public void useConstructorWithCause() {
		TestSystemException systemException = new TestSystemException("error message is: '%s'", new RuntimeException(), "details...");
		assertThat(systemException.getMessage(), is("error message is: 'details...'"));
		assertThat(systemException.getCause(), instanceOf(RuntimeException.class));
	}
	
	private class TestSystemException extends SystemException {
		
		private static final long serialVersionUID = 7500285468446033551L;
		
		protected TestSystemException(final String errorMessage, final Object... args) {
			super(errorMessage, args);
		}
		
		protected TestSystemException(final String errorMessage, final Exception cause, final Object... args) {
			super(errorMessage, cause, args);
		}
	}
}
