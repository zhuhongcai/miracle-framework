package com.miracle.framework.remote.netty.fixture;

import java.util.concurrent.Callable;

import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.netty.client.NettyClient;
import com.miracle.framework.remote.netty.fixture.service.FooService;

public final class AsyncQueryCallable implements Callable<Response> {
	
	private final NettyClient nettyClient;
	private final String queryString;
	
	public AsyncQueryCallable(final NettyClient nettyClient, final String queryString) {
		this.nettyClient = nettyClient;
		this.queryString = queryString;
	}
	
	@Override
	public Response call() throws Exception {
		return nettyClient.sent(new Request(FooService.class, "query", queryString));
	}

}
