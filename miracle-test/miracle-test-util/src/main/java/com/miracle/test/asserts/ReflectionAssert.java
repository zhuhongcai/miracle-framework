package com.miracle.test.asserts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Collection;

public final class ReflectionAssert {
	
	private ReflectionAssert() { }
	
	public static void assertFieldNames(final Collection<Field> actualFields, final Collection<String> expectedFieldNames) {
		if (null == actualFields) {
			fail("The actual fields is null.");
		}
		if (null == expectedFieldNames) {
			fail("The expected field names is null.");
		}
		assertThat("The size of actual fields and expected field names are not equal.", actualFields.size(), is(expectedFieldNames.size()));
		for (Field each : actualFields) {
			String fieldName =  each.getName();
			assertTrue(String.format("The expected field names %s don't contain field '%s'.", expectedFieldNames, fieldName), expectedFieldNames.contains(fieldName));
		}
	}
}
