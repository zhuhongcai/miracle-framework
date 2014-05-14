package com.miracle.framework.reflection;

import static com.miracle.test.asserts.ReflectionAssert.assertFieldNames;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.junit.Test;

import com.miracle.framework.reflection.ReflectionException;
import com.miracle.framework.reflection.ReflectionUtil;


public final class ReflectionUtilTest {
	
	@Test
	public void newInstanceSuccess() {
		ReflectionUtil.newInstance(Object.class);
	}
	
	@Test
	public void newInstanceFailureWhenUnInstanceableClass() {
		try {
			ReflectionUtil.newInstance(List.class);
		} catch (final ReflectionException ex) {
			assertThat(ex.getMessage(), is("Reflection fail, the reason is: java.util.List"));
		}
	}
	
	@Test
	public void getAllNonStaticFields() {
		assertFieldNames(ReflectionUtil.getAllNonStaticFields(ReflectionTestObject.class), 
				Arrays.asList(
						"testParentInt", 
						"testParentString", 
						"testParentDate", 
						"testGrandparentInt", 
						"testGrandparentString", 
						"testGrandparentDate", 
						"testInt", 
						"testString", 
						"testDate"));
	}
	
	@Test
	public void getNullPropertyNames() {
		TestObject testObject = new TestObject();
		assertThat(ReflectionUtil.getNullPropertyNames(testObject), is(new String[] {"field2", "field1"}));
		testObject.setField1(1);
		assertThat(ReflectionUtil.getNullPropertyNames(testObject), is(new String[] {"field2"}));
		testObject.setField2("1");
		assertThat(ReflectionUtil.getNullPropertyNames(testObject), is(new String[0]));
	}
	
	@Getter
	@Setter
	class TestObject {
		private Integer field1;
		private String field2;
	}
}
