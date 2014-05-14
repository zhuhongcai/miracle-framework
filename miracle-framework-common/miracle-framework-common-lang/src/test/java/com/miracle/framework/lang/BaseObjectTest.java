package com.miracle.framework.lang;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.miracle.framework.lang.fixture.Bar;
import com.miracle.framework.lang.fixture.Foo;

public final class BaseObjectTest {
	
	@Test
	public void hashCodeForIncludeAllFields() {
		assertThat(new Foo("foo").hashCode(), is(new Foo("foo").hashCode()));
		assertThat(new Foo("foo").hashCode(), not(new Foo("bar").hashCode()));
	}
	
	@Test
	public void hashCodeForExcludeSomeFields() {
		assertThat(new Bar("foo", "bar").hashCode(), is(new Bar("foo", "foo").hashCode()));
		assertThat(new Bar("foo", "bar").hashCode(), not(new Bar("bar", "bar").hashCode()));
	}
	
	@Test
	public void equalsForIncludeAllFields() {
		assertTrue(new Foo("foo").equals(new Foo("foo")));
		assertFalse(new Foo("foo").equals(new Foo("bar")));
	}
	
	@Test
	public void equalsForExcludeSomeFields() {
		assertTrue(new Bar("foo", "bar").equals(new Bar("foo", "bar")));
		assertFalse(new Bar("foo", "bar").equals(new Bar("bar", "foo")));
	}
	
	@Test
	public void testToString() {
		assertThat(new Foo("foo").toString(), endsWith("[foo=foo]"));
		assertThat(new Bar("foo", "bar").toString(), endsWith("[foo=foo]"));
	}
}
