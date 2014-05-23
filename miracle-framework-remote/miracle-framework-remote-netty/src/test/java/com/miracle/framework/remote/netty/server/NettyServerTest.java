package com.miracle.framework.remote.netty.server;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.miracle.framework.container.spring.SpringContainer;
import com.miracle.framework.remote.server.exception.ServerStopException;

@ContextConfiguration(locations = SpringContainer.CONFIG_FILE)
public final class NettyServerTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private NettyServer nettyServer;
	
	@Test(expected = ServerStopException.class)
	public void stopServerWithoutStart() {
		nettyServer.stop();
	}
	
	@Test
	public void stopServer() throws InterruptedException {
		nettyServer.start(1234);
		nettyServer.stop();
	}
}
