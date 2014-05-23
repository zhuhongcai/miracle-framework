package com.miracle.framework.remote.server.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class ServerExceptionTest {
	
	@Test
	public void getMessageId() {
		ServerException serverException = new ServerException(1L, new RuntimeException());
		assertThat(serverException.getMessageId(), is(1L));
	}
	
	@Test
	public void getCause() {
		ServerException serverException = new ServerException(1L, new RuntimeException());
		assertThat(serverException.getCause(), instanceOf(RuntimeException.class));
	}
}
