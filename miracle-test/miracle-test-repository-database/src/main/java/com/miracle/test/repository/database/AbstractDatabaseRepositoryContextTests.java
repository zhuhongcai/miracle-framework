package com.miracle.test.repository.database;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@ContextConfiguration(locations = "classpath:META-INF/spring/internal/root/testDatabaseRepositoryContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public abstract class AbstractDatabaseRepositoryContextTests extends AbstractJUnit4SpringContextTests { }
