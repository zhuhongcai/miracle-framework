package com.miracle.framework.remote.cluster.fixture;

import java.net.InetSocketAddress;

import com.miracle.framework.remote.cluster.AbstractRouter;
import com.miracle.framework.remote.cluster.LoadBalance;
import com.miracle.framework.remote.cluster.RandomLoadBalance;

public final class TestRouter extends AbstractRouter {
	
	private TestRegistry testRegistry = new TestRegistry();
	private RandomLoadBalance randomLoadBalance = new RandomLoadBalance(testRegistry);
	
	public TestRouter() {
		testRegistry.register(new InetSocketAddress("localhost", 80));
	}
	
	@Override
	protected LoadBalance getLoadBalance() {
		return randomLoadBalance;
	}
}
