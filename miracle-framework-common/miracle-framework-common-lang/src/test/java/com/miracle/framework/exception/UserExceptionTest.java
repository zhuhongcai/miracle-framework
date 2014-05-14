package com.miracle.framework.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.miracle.framework.exception.fixture.TestUserException;

public final class UserExceptionTest {
	
	@Test
	public void getErrorCode() {
		assertThat(new TestUserException().getErrorCode(), is("com.miracle.framework.exception.fixture.TestUserException"));
		assertThat(new TestUserException().getArguments(), is(""));
	}
}
