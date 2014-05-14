package com.miracle.framework.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.miracle.framework.exception.fixture.TestLogicException;

public final class LogicExceptionTest {
	
	@Test
	public void useDefaultConstructor() {
		assertNull(new TestLogicException().getMessage());
	}
	
	@Test
	public void useStringConstructor() {
		assertThat(new TestLogicException("error message").getMessage(), is("error message"));
	}
}
