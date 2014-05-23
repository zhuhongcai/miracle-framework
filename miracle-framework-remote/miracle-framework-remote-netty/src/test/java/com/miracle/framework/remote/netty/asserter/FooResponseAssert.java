package com.miracle.framework.remote.netty.asserter;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.netty.fixture.model.Foo;

public final class FooResponseAssert {
	
	private FooResponseAssert() { }
	
	public static void assertException(final Response actual, final Class<? extends Throwable> expectedException) {
		assertThat(actual.getReturnValue(), nullValue());
		assertThat(actual.getException(), instanceOf(expectedException));
	}
	
	public static void assertCauseException(final Response actual, final Class<? extends Throwable> expectedException) {
		assertThat(actual.getReturnValue(), nullValue());
		assertThat(actual.getException().getCause(), instanceOf(expectedException));
	}
	
	public static void assertNoReturnValue(final Response actual) {
		assertThat(actual.getReturnValue(), nullValue());
		assertThat(actual.getException(), nullValue());
	}
	
	public static void assertHasReturnValue(final Response actual, final String expected) {
		assertThat(((Foo) actual.getReturnValue()).getBar(), is(expected));
		assertThat(actual.getException(), nullValue());
	}
}
