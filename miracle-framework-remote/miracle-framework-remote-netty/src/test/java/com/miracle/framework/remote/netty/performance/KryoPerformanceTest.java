package com.miracle.framework.remote.netty.performance;

import org.springframework.test.context.ContextConfiguration;

import com.miracle.framework.container.spring.SpringContainer;

@ContextConfiguration(locations = { "classpath:META-INF/spring/external/KryoPerformanceContext.xml", SpringContainer.CONFIG_FILE })
public final class KryoPerformanceTest extends AbstractSerializePerformanceTests {
	
	public KryoPerformanceTest() {
		super(5001);
	}
}
