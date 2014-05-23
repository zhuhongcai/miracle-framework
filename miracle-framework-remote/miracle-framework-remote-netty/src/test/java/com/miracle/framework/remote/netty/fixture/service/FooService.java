package com.miracle.framework.remote.netty.fixture.service;

import com.miracle.framework.remote.netty.fixture.model.Foo;

public interface FooService {
	
	void update(Foo foo);
	Foo query(String bar) throws InterruptedException;
	Foo queryWithSystemException(String bar);
	Foo querySlow(String bar) throws InterruptedException;
}
