package com.miracle.framework.remote.cluster;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.miracle.framework.remote.cluster.fixture.TestRouter;
import com.miracle.framework.remote.exchange.Request;

public final class RouterTest {
	
	private TestRouter router = new TestRouter();
	
	@Test
	public void sent() {
		assertThat(router.sent(new Request(Object.class, "toString")), nullValue());
	}
}
