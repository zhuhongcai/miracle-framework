package com.miracle.framework.lang.fixture;

import com.miracle.framework.lang.BaseObject;

public final class Foo extends BaseObject {
	
	@SuppressWarnings("unused")
	private String foo;
	
	public Foo(final String field) {
		this.foo = field;
	}
}
