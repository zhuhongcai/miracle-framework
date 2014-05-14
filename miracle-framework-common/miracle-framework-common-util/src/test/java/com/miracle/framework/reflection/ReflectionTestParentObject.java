package com.miracle.framework.reflection;

import java.util.Date;

class ReflectionTestParentObject extends ReflectionTestGrandparentObject {
	
	private static int parentStaticIntField;
	private final int testParentInt = 0;
	private final String testParentString = null;
	private final Date testParentDate = null;
	
	static int getParentStaticIntField() {
		return parentStaticIntField;
	}
	
	int getTestParentInt() {
		return testParentInt;
	}
	
	String getTestParentString() {
		return testParentString;
	}
	
	Date getTestParentDate() {
		return testParentDate;
	}
}
