package com.miracle.framework.remote.client;

import java.net.InetSocketAddress;

import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;

public interface Client {
	
	void connect(InetSocketAddress socketAddress);
	Response sent(Request request);
	InetSocketAddress getRemoteAddress();
	void close();
}
