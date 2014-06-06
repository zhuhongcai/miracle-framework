package com.miracle.framework.remote.netty.performance;

import static com.miracle.framework.remote.netty.asserter.FooResponseAssert.assertHasReturnValue;

import java.net.InetSocketAddress;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.netty.client.NettyClient;
import com.miracle.framework.remote.netty.fixture.service.FooService;
import com.miracle.framework.remote.netty.server.NettyServer;

public abstract class AbstractSerializePerformanceTests extends AbstractJUnit4SpringContextTests {
	
	private final int loopTimes = 10000;
	
	private int port;
	private String ip = "localhost";
	
	@Resource
	private NettyServer nettyServer;
	
	@Resource
	private NettyClient nettyClient;
	
	@Resource
	private FooService fooService;
	
	protected AbstractSerializePerformanceTests(final int port) {
		this.port = port;
	}
	
	@Test
	public void doQuery() {
		nettyServer.start(port);
		nettyClient.connect(new InetSocketAddress(ip, port));
		for (int i = 0;i < loopTimes;i++) {
			Response response = nettyClient.sent(new Request(FooService.class, "query", "bar" + i));
			assertHasReturnValue(response, "bar" + i);
		}
	}
}
