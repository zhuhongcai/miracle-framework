package com.miracle.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.test.webmvc.AbstractApiControllerContextTestsTest;
import com.miracle.test.webmvc.util.BindingResultBuilderTest;

@RunWith(Suite.class)
@SuiteClasses({
	BindingResultBuilderTest.class, 
	AbstractApiControllerContextTestsTest.class
	}
)
public final class AllTestWebmvcTests { }
