package com.miracle.framework.common.core.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class UserExceptionTest {
	
	@Test
	public void getErrorCode() {
		assertThat(new TestUserException().getErrorCode(), is("com.miracle.framework.common.core.exception.UserExceptionTest.TestUserException"));
		assertThat(new TestUserException().getArguments(), is(""));
	}
	
	private class TestUserException extends UserException {
		
		private static final long serialVersionUID = -1682483464582525189L;
		
	}
}
