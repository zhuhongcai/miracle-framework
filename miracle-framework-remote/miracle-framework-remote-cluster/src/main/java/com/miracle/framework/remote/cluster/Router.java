package com.miracle.framework.remote.cluster;

import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;

public interface Router {
	
	Response sent(Request request);
}
