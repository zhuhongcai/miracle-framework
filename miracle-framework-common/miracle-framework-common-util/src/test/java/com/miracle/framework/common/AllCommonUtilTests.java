package com.miracle.framework.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.common.util.collection.ListTransformerTest;
import com.miracle.framework.common.util.encoding.EncodingTest;
import com.miracle.framework.common.util.reflection.ReflectionUtilTest;

@RunWith(Suite.class)
@SuiteClasses({
	ReflectionUtilTest.class, 
	EncodingTest.class, 
	ListTransformerTest.class
})
public final class AllCommonUtilTests { }
