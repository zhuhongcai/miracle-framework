package com.miracle.framework.common.util.reflection;

import java.util.Date;

class ReflectionTestGrandparentObject {
	
	private static int grandParentStaticIntField;
	private final int testGrandparentInt = 0;
	private final String testGrandparentString = null;
	private final Date testGrandparentDate = null;
	
	static int getGrandparentStaticIntField() {
		return grandParentStaticIntField;
	}
	
	int getTestGrandparentInt() {
		return testGrandparentInt;
	}
	
	String getTestGrandparentString() {
		return testGrandparentString;
	}
	
	Date getTestGrandparentDate() {
		return testGrandparentDate;
	}
}
