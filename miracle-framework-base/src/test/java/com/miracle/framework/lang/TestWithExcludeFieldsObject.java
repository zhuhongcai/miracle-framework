package com.miracle.framework.lang;

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
