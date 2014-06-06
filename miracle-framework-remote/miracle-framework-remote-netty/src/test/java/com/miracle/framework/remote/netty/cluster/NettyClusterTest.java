package com.miracle.framework.remote.netty.cluster;

import static com.miracle.framework.remote.netty.asserter.FooResponseAssert.assertHasReturnValue;

import java.net.InetSocketAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.miracle.framework.container.spring.SpringContainer;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.netty.fixture.service.FooService;
import com.miracle.framework.remote.netty.server.NettyServer;

public final class NettyClusterTest {
	
	private AbstractApplicationContext context1;
	private AbstractApplicationContext context2;
	private AbstractApplicationContext context3;
	
	private NettyServer nettyServer1;
	private NettyServer nettyServer2;
	
	private NettyRegistry nettyRegistry;
	private NettyRouter nettyRouter;
	
	private int port1 = 8898;
	private int port2 = 8899;
	private String ip = "localhost";
	
	@Before
	public void setUp() {
		context1 = new ClassPathXmlApplicationContext(SpringContainer.CONFIG_FILE);
		nettyServer1 = context1.getBean(NettyServer.class);
		context2 = new ClassPathXmlApplicationContext(SpringContainer.CONFIG_FILE);
		nettyServer2 = context2.getBean(NettyServer.class);
		
		context3 = new ClassPathXmlApplicationContext(SpringContainer.CONFIG_FILE);
		nettyRegistry = context3.getBean(NettyRegistry.class);
		nettyRouter = context3.getBean(NettyRouter.class);
		
		nettyServer1.start(port1);
		nettyServer2.start(port2);
	}
	
	@After
	public void tearDown() {
		context1.close();
		context2.close();
		context3.close();
	}

	@Test
	public void queryForSync() {
		nettyRegistry.register(new InetSocketAddress(ip, port1));
		nettyRegistry.register(new InetSocketAddress(ip, port2));
		nettyRegistry.register(new InetSocketAddress(ip, port1));
		for (int i = 0;i < 100;i++) {
			Response response = nettyRouter.sent(new Request(FooService.class, "query", "bar" + i));
			assertHasReturnValue(response, "bar" + i);
		}
	}
}
