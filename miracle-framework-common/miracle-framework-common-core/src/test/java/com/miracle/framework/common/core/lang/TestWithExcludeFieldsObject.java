package com.miracle.framework.common.core.lang;

import com.miracle.framework.common.core.lang.BaseObject;

final class TestWithExcludeFieldsObject extends BaseObject {
	
	@SuppressWarnings("unused")
	private String field;
	
	@SuppressWarnings("unused")
	private String excludeField;
	
	TestWithExcludeFieldsObject(final String field, final String excludeField) {
		super("excludeField");
		this.field = field;
		this.excludeField = excludeField;
	}
}
