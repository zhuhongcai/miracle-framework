package com.miracle.framework.remote.exchange;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class ResponseTest {
	
	@Test
	public void infomationForReturnValue() {
		Response response = new Response(1L, "value");
		assertThat(response.getReturnValue().toString(), is("value"));
		assertThat(response.getException(), nullValue());
		assertThat(response.getMessageId(), is(1L));
	}
	
	@Test
	public void infomationForException() {
		Response response = new Response(1L, new RuntimeException());
		assertThat(response.getReturnValue(), nullValue());
		assertThat(response.getException(), instanceOf(RuntimeException.class));
		assertThat(response.getMessageId(), is(1L));
	}
}
