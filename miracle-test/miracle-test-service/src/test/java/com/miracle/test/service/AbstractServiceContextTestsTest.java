package com.miracle.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.miracle.test.service.fixture.ManagedService;
import com.miracle.test.service.fixture.FooService;
import com.miracle.test.service.fixture.UnmanagedService;
import com.miracle.test.service.fixture.internal.UnmanagedServiceImpl;

public final class AbstractServiceContextTestsTest extends AbstractServiceContextTests {
	
	@Resource
	@InjectMocks
	private FooService fooService;
	
	@Resource
	@Mock
	private ManagedService managedService;
	
	@Mock
	private UnmanagedService unmanagedService = new UnmanagedServiceImpl();
	
	@Test
	public void sayHello() throws IllegalAccessException {
		when(managedService.sayHello()).thenReturn("mocked hello");
		when(unmanagedService.getDate()).thenReturn(new SimpleDateFormat("yyyy-MM-dd").format(new Date(0L)));
		assertThat(fooService.getMessage(), is("1970-01-01 : mocked hello"));
		verify(managedService).sayHello();
		verify(unmanagedService).getDate();
	}
}
