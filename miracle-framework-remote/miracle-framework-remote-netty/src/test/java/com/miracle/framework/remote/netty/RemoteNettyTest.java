package com.miracle.framework.remote.netty;

import static com.miracle.framework.remote.netty.asserter.FooResponseAssert.assertCauseException;
import static com.miracle.framework.remote.netty.asserter.FooResponseAssert.assertException;
import static com.miracle.framework.remote.netty.asserter.FooResponseAssert.assertHasReturnValue;
import static com.miracle.framework.remote.netty.asserter.FooResponseAssert.assertNoReturnValue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.miracle.framework.container.spring.SpringContainer;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.netty.client.NettyClient;
import com.miracle.framework.remote.netty.fixture.AsyncQueryCallable;
import com.miracle.framework.remote.netty.fixture.model.Foo;
import com.miracle.framework.remote.netty.fixture.service.FooService;
import com.miracle.framework.remote.netty.server.NettyServer;
import com.miracle.framework.remote.server.exception.ServerException;

@ContextConfiguration(locations = SpringContainer.CONFIG_FILE)
public final class RemoteNettyTest extends AbstractJUnit4SpringContextTests {
	
	private static volatile boolean channelOpened = false;
	
	private int port = 6099;
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
		assertCauseException(response, ReflectiveOperationException.class);
	}
	
	@Test
	public void update() {
		Response response = nettyClient.sent(new Request(FooService.class, "update", new Foo("bar")));
		assertNoReturnValue(response);
	}
	
	@Test
	public void queryForSync() {
		for (int i = 0;i < 100;i++) {
			Response response = nettyClient.sent(new Request(FooService.class, "query", "bar" + i));
			assertHasReturnValue(response, "bar" + i);
		}
	}
	
	@Test
	public void queryForAsync() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(30);
		for (int i = 0;i < 100;i++) {
			Response response = executorService.submit(new AsyncQueryCallable(nettyClient, "bar" + i)).get();
			assertHasReturnValue(response, "bar" + i);
		}
	}
	
	@Test
	public void queryWithSystemException() {
		Response response = nettyClient.sent(new Request(FooService.class, "queryWithSystemException", "bar"));
		assertException(response, ServerException.class);
	}
	
	@Test
	public void queryMix() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Response normalQueryResponse = executorService.submit(new AsyncQueryCallable(nettyClient, "normal")).get();
		Response slowQueryResponse = executorService.submit(new AsyncQueryCallable(nettyClient, "slow")).get();
		assertHasReturnValue(slowQueryResponse, "slow");
		assertHasReturnValue(normalQueryResponse, "normal");
	}
}
