package com.miracle.framework.remote.client;

import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;

public interface Client {
	
	void connect(String ip, int port);
	Response sent(Request request);
	void close();
}
