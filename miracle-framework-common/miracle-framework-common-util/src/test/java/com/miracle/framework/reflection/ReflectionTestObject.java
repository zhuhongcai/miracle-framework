package com.miracle.framework.reflection;

import java.util.Date;

class ReflectionTestObject extends ReflectionTestParentObject {
	
	private static int staticIntField = 1;
	private int testInt;
	private String testString;
	private Date testDate;
	
	static int getStaticIntField() {
		return staticIntField;
	}
	
	int getTestInt() {
		return testInt;
	}
	
	String getTestString() {
		return testString;
	}
	
	Date getTestDate() {
		return testDate;
	}
}
