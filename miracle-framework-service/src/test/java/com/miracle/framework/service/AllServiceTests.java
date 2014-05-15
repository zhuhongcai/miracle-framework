package com.miracle.framework.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.service.domain.AbstractPersistableBusinessObjectAdapterTest;
import com.miracle.framework.service.domain.AbstractPersistableBusinessObjectTest;

@RunWith(Suite.class)
@SuiteClasses({
	AbstractPersistableBusinessObjectTest.class, 
	AbstractPersistableBusinessObjectAdapterTest.class
	}
)
public final class AllServiceTests { }
