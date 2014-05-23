package com.miracle.framework.remote.netty.fixture.service.internal;

import org.springframework.stereotype.Service;

import com.miracle.framework.remote.netty.fixture.exception.FooSystemException;
import com.miracle.framework.remote.netty.fixture.model.Foo;
import com.miracle.framework.remote.netty.fixture.service.FooService;

@Service
public class FooServiceImpl implements FooService {
	
	@Override
	public void update(final Foo foo) { }
	
	@Override
	public Foo query(final String bar) throws InterruptedException {
		if ("slow".equals(bar)) {
			Thread.sleep(300L);
		}
		return new Foo(bar);
	}
	
	@Override
	public Foo queryWithSystemException(final String bar) {
		throw new FooSystemException();
	}
}
