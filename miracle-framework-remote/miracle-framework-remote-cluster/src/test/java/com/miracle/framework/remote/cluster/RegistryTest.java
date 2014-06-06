package com.miracle.framework.remote.cluster;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.InetSocketAddress;

import org.junit.Test;

import com.miracle.framework.remote.cluster.fixture.TestRegistry;

public final class RegistryTest {
	
	@Test
	public void registerSuccess() {
		TestRegistry testRegistry = new TestRegistry();
		testRegistry.register(new InetSocketAddress("localhost", 80));
		testRegistry.register(new InetSocketAddress("localhost", 8080));
		assertThat(testRegistry.getRegisteredClients().size(), is(2));
		assertThat(testRegistry.getRegisteredClients().get(0).getRemoteAddress(), is(new InetSocketAddress("localhost", 80)));
		assertThat(testRegistry.getRegisteredClients().get(1).getRemoteAddress(), is(new InetSocketAddress("localhost", 8080)));
	}
	
	@Test
	public void registerDuplicate() {
		TestRegistry testRegistry = new TestRegistry();
		testRegistry.register(new InetSocketAddress("localhost", 80));
		testRegistry.register(new InetSocketAddress("localhost", 80));
		assertThat(testRegistry.getRegisteredClients().size(), is(1));
		assertThat(testRegistry.getRegisteredClients().get(0).getRemoteAddress(), is(new InetSocketAddress("localhost", 80)));
	}
	
	@Test
	public void unregisterSuccess() {
		TestRegistry testRegistry = new TestRegistry();
		testRegistry.register(new InetSocketAddress("localhost", 80));
		testRegistry.register(new InetSocketAddress("localhost", 8080));
		testRegistry.unregister(new InetSocketAddress("localhost", 8080));
		assertThat(testRegistry.getRegisteredClients().size(), is(1));
		assertThat(testRegistry.getRegisteredClients().get(0).getRemoteAddress(), is(new InetSocketAddress("localhost", 80)));
	}
	
	@Test
	public void unregisterWithoutregistered() {
		TestRegistry testRegistry = new TestRegistry();
		testRegistry.register(new InetSocketAddress("localhost", 80));
		testRegistry.unregister(new InetSocketAddress("localhost", 8080));
		assertThat(testRegistry.getRegisteredClients().size(), is(1));
		assertThat(testRegistry.getRegisteredClients().get(0).getRemoteAddress(), is(new InetSocketAddress("localhost", 80)));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void modifyRegisteredClients() {
		TestRegistry testRegistry = new TestRegistry();
		testRegistry.register(new InetSocketAddress("localhost", 80));
		testRegistry.register(new InetSocketAddress("localhost", 8080));
		testRegistry.getRegisteredClients().remove(0);
	}
}
