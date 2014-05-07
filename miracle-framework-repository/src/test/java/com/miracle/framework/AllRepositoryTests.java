package com.miracle.framework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.repository.BaseJpaRepositoryTest;
import com.miracle.framework.repository.exception.OptimisticLockingExceptionTest;

@RunWith(Suite.class)
@SuiteClasses({
	OptimisticLockingExceptionTest.class, 
	BaseJpaRepositoryTest.class
	}
)
public final class AllRepositoryTests { }
