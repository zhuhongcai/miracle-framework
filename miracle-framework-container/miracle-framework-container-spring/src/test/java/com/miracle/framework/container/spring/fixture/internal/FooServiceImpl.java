package com.miracle.framework.container.spring.fixture.internal;

import org.springframework.stereotype.Service;

import com.miracle.framework.container.spring.fixture.FooService;

@Service
public class FooServiceImpl implements FooService {

	@Override
	public String foo() {
		return "foo";
	}
}
