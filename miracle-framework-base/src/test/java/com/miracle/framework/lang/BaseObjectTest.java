package com.miracle.framework.lang;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class BaseObjectTest {
	
	@Test
	public void hashCodeForIncludeAllFields() {
		assertThat(new TestObject("1").hashCode(), is(new TestObject("1").hashCode()));
		assertThat(new TestObject("1").hashCode(), not(new TestObject("2").hashCode()));
	}
	
	@Test
	public void hashCodeForExcludeSomeFields() {
		assertThat(new TestWithExcludeFieldsObject("1", "1").hashCode(), is(new TestWithExcludeFieldsObject("1", "2").hashCode()));
		assertThat(new TestWithExcludeFieldsObject("1", "1").hashCode(), not(new TestWithExcludeFieldsObject("2", "1").hashCode()));
	}
	
	@Test
	public void equalsForIncludeAllFields() {
		assertTrue(new TestObject("1").equals(new TestObject("1")));
		assertFalse(new TestObject("1").equals(new TestObject("2")));
	}
	
	@Test
	public void equalsForExcludeSomeFields() {
		assertTrue(new TestWithExcludeFieldsObject("1", "1").equals(new TestWithExcludeFieldsObject("1", "2")));
		assertFalse(new TestWithExcludeFieldsObject("1", "1").equals(new TestWithExcludeFieldsObject("2", "1")));
	}
	
	@Test
	public void testToString() {
		assertThat(new TestObject("1").toString(), endsWith("[field=1]"));
		assertThat(new TestWithExcludeFieldsObject("1", "1").toString(), endsWith("[field=1]"));
	}
}
