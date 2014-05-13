package com.miracle.framework.container.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.miracle.framework.container.Context;

public final class SpringContext implements Context<ClassPathXmlApplicationContext> {
	
	private final ClassPathXmlApplicationContext applicationContext;
	
	public SpringContext(final ClassPathXmlApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public ClassPathXmlApplicationContext get() {
		return applicationContext;
	}

}
