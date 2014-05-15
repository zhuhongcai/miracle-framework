package com.miracle.test.webmvc.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.miracle.test.webmvc.util.BindingResultBuilder;

public final class BindingResultBuilderTest {
	
	@Test
	public void correctResult() {
		assertFalse(BindingResultBuilder.correctResult().hasErrors());
	}
	
	@Test
	public void rejectedResult() {
		assertTrue(BindingResultBuilder.rejectedResult().hasErrors());
	}
}
