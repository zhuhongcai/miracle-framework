package com.miracle.framework.remote.cluster.fixture;

import java.net.InetSocketAddress;

import com.miracle.framework.remote.client.Client;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;

public final class TestClient implements Client {
	
	private InetSocketAddress remoteAddress;
	
	@Override
	public void connect(InetSocketAddress socketAddress) {
		remoteAddress = socketAddress;
	}
	
	@Override
	public Response sent(Request request) {
		return null;
	}
	
	@Override
	public InetSocketAddress getRemoteAddress() {
		return remoteAddress;
	}
	
	@Override
	public void close() { }
}
