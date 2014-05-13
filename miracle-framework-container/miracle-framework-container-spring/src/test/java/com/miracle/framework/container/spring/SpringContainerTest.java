package com.miracle.framework.container.spring;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class SpringContainerTest {
	
	private SpringContainer springContainer = new SpringContainer();
	
	@Test
	public void start() {
		springContainer.start();
		assertThat(springContainer.getContext(), notNullValue());
	}
	
	@Test
	public void stop() {
		springContainer.start();
		assertThat(springContainer.getContext(), notNullValue());
		springContainer.stop();
		assertThat(springContainer.getContext(), nullValue());
	}
	
	@Test
	public void stopWhenNotStart() {
		springContainer.stop();
		assertThat(springContainer.getContext(), nullValue());
	}
}
