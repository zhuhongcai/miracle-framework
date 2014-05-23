package com.miracle.framework.remote.server.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class ServerStopExceptionTest {
	
	@Test
	public void getMessage() {
		ServerStopException serverStopException = new ServerStopException();
		assertThat(serverStopException.getMessage(), is("Can't stop this server, because the server didn't start yet."));
	}
}
