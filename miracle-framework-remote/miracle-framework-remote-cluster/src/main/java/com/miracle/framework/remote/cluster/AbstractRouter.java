package com.miracle.framework.remote.cluster;

import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;

public abstract class AbstractRouter implements Router {
	
	@Override
	public Response sent(final Request request) {
		return getLoadBalance().select().sent(request);
	}
	
	protected abstract LoadBalance getLoadBalance();
}
