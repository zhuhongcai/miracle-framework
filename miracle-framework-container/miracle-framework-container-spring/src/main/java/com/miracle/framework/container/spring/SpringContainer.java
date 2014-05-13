package com.miracle.framework.container.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.miracle.framework.container.Container;

public final class SpringContainer implements Container {
	
	private final String springConfigFile = "classpath:META-INF/spring/internal/root/applicationContext.xml";
	
	private SpringContext springContext;
	
	@Override
	public void start() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfigFile);
		springContext = new SpringContext(context);
		context.start();
	}
	
	@Override
	public void stop() {
		if (null != springContext && null != springContext.get()) {
			springContext.get().close();
			springContext = null;
		}
	}
	
	@Override
	public SpringContext getContext() {
		return springContext;
	}
}
