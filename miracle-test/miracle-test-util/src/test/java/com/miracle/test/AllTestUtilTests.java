package com.miracle.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.test.asserts.ReflectionAssertTest;
import com.miracle.test.enums.TestEnumTest;

@RunWith(Suite.class)
@SuiteClasses({
	TestEnumTest.class, 
	ReflectionAssertTest.class
	}
)
public final class AllTestUtilTests { }
