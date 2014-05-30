package com.miracle.framework.remote.netty.performance;

import org.springframework.test.context.ContextConfiguration;

import com.miracle.framework.container.spring.SpringContainer;

@ContextConfiguration(locations = { "classpath:META-INF/spring/external/JavaPerformanceContext.xml", SpringContainer.CONFIG_FILE })
public final class JavaSerializePerformanceTest extends AbstractSerializePerformanceTests {
	
	public JavaSerializePerformanceTest() {
		super(5010);
	}
}
