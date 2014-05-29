package com.miracle.framework.lang;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.lang.serialize.kryo.KryoSerializationTest;

@RunWith(Suite.class)
@SuiteClasses(
	KryoSerializationTest.class
)
public final class AllSerializeTests { }
