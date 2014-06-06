package com.miracle.framework.remote.cluster;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.miracle.framework.remote.cluster.fixture.TestRegistry;

public final class RandomLoadBalanceTest {
	
	private TestRegistry testRegistry = new TestRegistry();
	private List<InetSocketAddress> registeredRemoteAddresses = new ArrayList<>(2);
	private RandomLoadBalance randomLoadBalance = new RandomLoadBalance(testRegistry);
	
	@Before
	public void setUp() {
		InetSocketAddress address1 = new InetSocketAddress("localhost", 80);
		testRegistry.register(address1);
		registeredRemoteAddresses.add(address1);
		InetSocketAddress address2 = new InetSocketAddress("localhost", 8080);
		testRegistry.register(address2);
		registeredRemoteAddresses.add(address2);
	}
	
	@Test
	public void select() {
		for (int i = 0;i < 10;i++) {
			assertThat(registeredRemoteAddresses, hasItem(randomLoadBalance.select().getRemoteAddress()));
		}
	}
}
