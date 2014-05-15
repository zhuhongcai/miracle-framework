package com.miracle.framework.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.repository.jpa.exception.OptimisticLockingExceptionTest;
import com.miracle.framework.repository.jpa.repository.BaseJpaRepositoryTest;

@RunWith(Suite.class)
@SuiteClasses({
	OptimisticLockingExceptionTest.class, 
	BaseJpaRepositoryTest.class
	}
)
public final class AllRepositoryJpaTests { }
