package com.miracle.framework.remote.exchange;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

import org.junit.Test;

public final class RequestTest {
	
	@Test
	public void allInfomation() {
		Request resquest = new Request(Object.class, "toString");
		assertThat(resquest.getApiClass(), isA(Object.class));
		assertThat(resquest.getMethod(), is("toString"));
		assertThat(resquest.getParameters(), is(new Object[] {}));
		assertThat(resquest.getMessageId(), not(0L));
	}
}
