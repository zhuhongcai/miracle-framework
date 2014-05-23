package com.miracle.framework.remote.client.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class ClientCloseExceptionTest {
	
	@Test
	public void getMessage() {
		ClientCloseException clientCloseException = new ClientCloseException();
		assertThat(clientCloseException.getMessage(), is("Can't close this client, beacuse the client didn't connect a server."));
	}
}
