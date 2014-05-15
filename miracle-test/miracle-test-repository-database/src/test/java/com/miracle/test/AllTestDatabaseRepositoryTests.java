package com.miracle.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.test.repository.database.AbstractDatabaseRepositoryContextTestsTest;

@RunWith(Suite.class)
@SuiteClasses(
	AbstractDatabaseRepositoryContextTestsTest.class
)
public class AllTestDatabaseRepositoryTests {

}
