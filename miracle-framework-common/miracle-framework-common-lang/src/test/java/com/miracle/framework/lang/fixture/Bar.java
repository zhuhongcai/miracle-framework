package com.miracle.framework.lang.fixture;

import com.miracle.framework.lang.BaseObject;

public final class Bar extends BaseObject {
	
	@SuppressWarnings("unused")
	private String foo;
	
	@SuppressWarnings("unused")
	private String bar;
	
	public Bar(final String foo, final String bar) {
		super("bar");
		this.foo = foo;
		this.bar = bar;
	}
}
