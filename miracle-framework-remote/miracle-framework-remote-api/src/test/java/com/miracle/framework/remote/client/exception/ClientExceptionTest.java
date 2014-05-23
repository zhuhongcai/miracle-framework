package com.miracle.framework.remote.client.exception;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class ClientExceptionTest {
	
	@Test
	public void getCause() {
		ClientException clientException = new ClientException(new RuntimeException());
		assertThat(clientException.getCause(), instanceOf(RuntimeException.class));
	}
}
