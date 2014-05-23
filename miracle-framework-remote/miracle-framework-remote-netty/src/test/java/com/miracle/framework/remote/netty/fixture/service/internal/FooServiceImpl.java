package com.miracle.framework.remote.netty.fixture.service.internal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.miracle.framework.remote.netty.fixture.exception.FooSystemException;
import com.miracle.framework.remote.netty.fixture.model.Foo;
import com.miracle.framework.remote.netty.fixture.service.FooService;

@Service
public class FooServiceImpl implements FooService {
	
	@Value("${client.timeout.seconds}")
	private int timeoutSeconds;
	
	@Override
	public void update(final Foo foo) { }
	
	@Override
	public Foo query(final String bar) throws InterruptedException {
		if ("slow".equals(bar) || "bar1".equals(bar)) {
			Thread.sleep(1000L);
		}
		return new Foo(bar);
	}
	
	@Override
	public Foo queryWithSystemException(final String bar) {
		throw new FooSystemException();
	}
	
	@Override
	public Foo querySlow(final String bar) throws InterruptedException {
		Thread.sleep(timeoutSeconds * 1000L + 1000L);
		return new Foo(bar);
	}
}
