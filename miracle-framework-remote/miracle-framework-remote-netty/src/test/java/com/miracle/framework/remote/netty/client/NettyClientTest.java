package com.miracle.framework.remote.netty.client;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.miracle.framework.container.spring.SpringContainer;
import com.miracle.framework.remote.client.exception.ClientCloseException;
import com.miracle.framework.remote.netty.server.NettyServer;

@ContextConfiguration(locations = SpringContainer.CONFIG_FILE)
public final class NettyClientTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private NettyServer nettyServer;
	
	@Resource
	private NettyClient nettyClient;
	
	@Test(expected = ClientCloseException.class)
	public void closeClientWithoutConnect() {
		nettyClient.close();
	}
	
	@Test
	public void closeClient() {
		nettyServer.start(2345);
		nettyClient.connect("localhost", 2345);
		nettyClient.close();
	}
}
