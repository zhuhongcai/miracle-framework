package com.miracle.framework.lang;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class ConstantsTest {
	
	@Test
	public void getLineSeparator() {
		assertThat(Constants.LINE_SEPARATOR, is(System.getProperty("line.separator")));
	}
}
