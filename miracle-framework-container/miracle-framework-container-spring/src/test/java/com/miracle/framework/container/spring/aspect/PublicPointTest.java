package com.miracle.framework.container.spring.aspect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.miracle.framework.container.spring.fixture.FooService;

@ContextConfiguration(locations = "classpath:META-INF/spring/internal/root/applicationContext.xml")
public class PublicPointTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private FooService fooService;
	
	@Test
	public void publicPointAspect() {
		assertThat(fooService.foo(), is("foobar"));
	}
}
