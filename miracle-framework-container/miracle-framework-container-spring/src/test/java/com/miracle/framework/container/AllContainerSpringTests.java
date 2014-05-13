package com.miracle.framework.container;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.container.spring.SpringContainerTest;
import com.miracle.framework.container.spring.aspect.PublicPointTest;

@RunWith(Suite.class)
@SuiteClasses({
	SpringContainerTest.class, 
	PublicPointTest.class
	}
)
public class AllContainerSpringTests { }
