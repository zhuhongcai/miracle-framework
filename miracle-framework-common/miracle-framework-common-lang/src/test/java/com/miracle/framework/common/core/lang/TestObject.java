package com.miracle.framework.common.core.lang;

import com.miracle.framework.common.core.lang.BaseObject;

final class TestObject extends BaseObject {
	
	@SuppressWarnings("unused")
	private String field;
	
	TestObject(final String field) {
		this.field = field;
	}
}
