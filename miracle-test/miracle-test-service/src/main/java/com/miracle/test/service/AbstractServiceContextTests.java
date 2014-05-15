package com.miracle.test.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.miracle.test.service.mock.MockitoAnnotationsInitTestExecutionListener;

@ContextConfiguration(locations = "classpath:META-INF/spring/internal/root/testServiceContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoAnnotationsInitTestExecutionListener.class })
public abstract class AbstractServiceContextTests extends AbstractJUnit4SpringContextTests { }
