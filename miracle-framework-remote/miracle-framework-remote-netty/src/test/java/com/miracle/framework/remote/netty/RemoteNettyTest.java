package com.miracle.framework.remote.netty;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.miracle.framework.container.spring.SpringContainer;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.netty.client.NettyClient;
import com.miracle.framework.remote.netty.fixture.model.Foo;
import com.miracle.framework.remote.netty.fixture.service.FooService;
import com.miracle.framework.remote.netty.server.NettyServer;
import com.miracle.framework.remote.server.exception.ServerException;
import com.miracle.framework.remote.server.exception.ServerTimeoutException;

@ContextConfiguration(locations = SpringContainer.CONFIG_FILE)
public final class RemoteNettyTest extends AbstractJUnit4SpringContextTests {
	
	private static volatile boolean channelOpened = false;
	
	private int port = 7099;
	private String ip = "localhost";
	
	@Resource
	private NettyServer nettyServer;
	
	@Resource
	private NettyClient nettyClient;
	
	@Resource
	private FooService fooService;
	
	@Before
	public void setUp() {
		if (!channelOpened) {
			nettyServer.start(port);
			nettyClient.connect(ip, port);
			channelOpened = true;
		}
	}
	
	@Test
	public void cannotFoundMethod() {
		Response response = nettyClient.sent(new Request(FooService.class, "notExist"));
		assertThat(response.getReturnValue(), nullValue());
		assertThat(response.getException().getCause(), instanceOf(ReflectiveOperationException.class));
	}
	
	@Test
	public void update() {
		Response response = nettyClient.sent(new Request(FooService.class, "update", new Foo("bar")));
		assertThat(response.getReturnValue(), nullValue());
		assertThat(response.getException(), nullValue());
	}
	
	@Test
	public void queryForSync() {
		for (int i = 0;i < 100;i++) {
			Response response = nettyClient.sent(new Request(FooService.class, "query", "bar" + i));
			assertThat(((Foo) response.getReturnValue()).getBar(), is("bar" + i));
			assertThat(response.getException(), nullValue());
		}
	}
	
	@Test(expected = ServerTimeoutException.class)
	@Ignore
	public void querySlow() {
		Response response = nettyClient.sent(new Request(FooService.class, "querySlow", "bar"));
		assertThat(((Foo) response.getReturnValue()).getBar(), is("bar"));
		assertThat(response.getException(), nullValue());
	}
	
	@Test
	public void queryWithSystemException() {
		Response response = nettyClient.sent(new Request(FooService.class, "queryWithSystemException", "bar"));
		assertThat(response.getReturnValue(), nullValue());
		assertThat(response.getException(), instanceOf(ServerException.class));
	}
	
	@Test
	@Ignore
	public void queryForAsync() throws InterruptedException {
		Executor executor = Executors.newFixedThreadPool(2);
		Runnable runnable1 = new Runnable() {

			@Override
			public void run() {
				Response response = nettyClient.sent(new Request(FooService.class, "query", "bar1"));
				assertThat(((Foo) response.getReturnValue()).getBar(), is("bar1"));
				assertThat(response.getException(), nullValue());
			}
		};
		
		Runnable runnable2 = new Runnable() {

			@Override
			public void run() {
				Response response = nettyClient.sent(new Request(FooService.class, "query", "bar2"));
				assertThat(((Foo) response.getReturnValue()).getBar(), is("bar2"));
				assertThat(response.getException(), nullValue());
			}
		};
		
		executor.execute(runnable1);
		executor.execute(runnable2);
		
		Thread.sleep(2000);
	}
}
