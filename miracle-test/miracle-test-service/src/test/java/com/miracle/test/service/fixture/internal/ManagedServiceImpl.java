package com.miracle.test.service.fixture.internal;

import org.springframework.stereotype.Service;

import com.miracle.test.service.fixture.ManagedService;

@Service
public final class ManagedServiceImpl implements ManagedService {
	
	@Override
	public String sayHello() {
		return "hello";
	}
}
