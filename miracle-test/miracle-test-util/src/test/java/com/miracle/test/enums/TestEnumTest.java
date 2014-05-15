package com.miracle.test.enums;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class TestEnumTest {
	
	@Test
	public void browseTestEnumInfo() {
		assertThat(TestEnum.values().length, is(1));
		assertThat(TestEnum.values()[0], is(TestEnum.TEST));
	}
}
