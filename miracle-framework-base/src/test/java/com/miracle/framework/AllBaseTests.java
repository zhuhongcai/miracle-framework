package com.miracle.framework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.collection.ListTransformerTest;
import com.miracle.framework.encoding.EncodingTest;
import com.miracle.framework.exception.LogicExceptionTest;
import com.miracle.framework.exception.SystemExceptionTest;
import com.miracle.framework.exception.UserExceptionTest;
import com.miracle.framework.lang.BaseObjectTest;
import com.miracle.framework.reflection.ReflectionUtilTest;

@RunWith(Suite.class)
@SuiteClasses({
	BaseObjectTest.class, 
	SystemExceptionTest.class, 
	LogicExceptionTest.class, 
	UserExceptionTest.class, 
	ReflectionUtilTest.class, 
	EncodingTest.class, 
	ListTransformerTest.class
})
public final class AllBaseTests { }
