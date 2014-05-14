package com.miracle.framework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.exception.LogicExceptionTest;
import com.miracle.framework.exception.SystemExceptionTest;
import com.miracle.framework.exception.UserExceptionTest;
import com.miracle.framework.lang.BaseObjectTest;

@RunWith(Suite.class)
@SuiteClasses({
	BaseObjectTest.class, 
	SystemExceptionTest.class, 
	LogicExceptionTest.class, 
	UserExceptionTest.class
})
public final class AllCommonCoreTests { }
