package com.miracle.test.asserts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public final class ReflectionAssertTest {
	
	@Test
	public void assertFieldNamesFailureWhenActualFieldsIsNull() {
		try {
			ReflectionAssert.assertFieldNames(null, Collections.<String>emptyList());
		} catch (final AssertionError ex) {
			assertThat(ex.getMessage(), is("The actual fields is null."));
		}
	}
	
	@Test
	public void assertFieldNamesFailureWhenExpectedFieldNamesIsNull() {
		try {
			ReflectionAssert.assertFieldNames(Collections.<Field>emptyList(), null);
		} catch (final AssertionError ex) {
			assertThat(ex.getMessage(), is("The expected field names is null."));
		}
	}
	
	@Test
	public void assertFieldNamesFailureWhenSizeMismatching() {
		try {
			ReflectionAssert.assertFieldNames(Collections.<Field>emptyList(), Arrays.asList("test"));
		} catch (final AssertionError ex) {
			assertThat(ex.getMessage(), is("The size of actual fields and expected field names are not equal.\nExpected: is <1>\n     but: was <0>"));
		}
	}
	
	@Test
	public void assertFieldNamesFailureWhenFieldMismatching() {
		try {
			ReflectionAssert.assertFieldNames(Arrays.asList(String.class.getFields()), Arrays.asList("test"));
		} catch (final AssertionError ex) {
			assertThat(ex.getMessage(), is("The expected field names [test] don't contain field 'CASE_INSENSITIVE_ORDER'."));
		}
	}
	
	@Test
	public void assertFieldNamesSucess() {
		ReflectionAssert.assertFieldNames(Arrays.asList(String.class.getFields()), Arrays.asList("CASE_INSENSITIVE_ORDER"));
	}
}

