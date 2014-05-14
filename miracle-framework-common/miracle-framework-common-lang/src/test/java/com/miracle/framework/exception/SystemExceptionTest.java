package com.miracle.framework.exception;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.miracle.framework.exception.fixture.TestSystemException;

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
}
