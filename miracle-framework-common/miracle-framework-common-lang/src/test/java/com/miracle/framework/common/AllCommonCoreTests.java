package com.miracle.framework.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.common.core.exception.LogicExceptionTest;
import com.miracle.framework.common.core.exception.SystemExceptionTest;
import com.miracle.framework.common.core.exception.UserExceptionTest;
import com.miracle.framework.common.core.lang.BaseObjectTest;

@RunWith(Suite.class)
@SuiteClasses({
	BaseObjectTest.class, 
	SystemExceptionTest.class, 
	LogicExceptionTest.class, 
	UserExceptionTest.class
})
public final class AllCommonCoreTests { }
