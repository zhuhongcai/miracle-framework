package com.miracle.test.service.mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public final class MockitoAnnotationsInitTestExecutionListener extends AbstractTestExecutionListener {
	
	@Override
	public void prepareTestInstance(TestContext testContext) {
		MockitoAnnotations.initMocks(testContext.getTestInstance());
	}
}
