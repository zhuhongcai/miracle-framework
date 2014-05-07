package com.miracle.framework.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.miracle.test.enums.TestEnum;

public final class ListTransformerTest {
	
	@Test
	public void encodingFromIso8859ToUtf8() {
		assertThat(ListTransformer.encodingFromIso8859ToUtf8(Arrays.asList("1", "2", "3")), is(Arrays.asList("1", "2", "3")));
	}
	
	@Test
	public void transformStringToEnumSuccess() {
		assertThat(ListTransformer.transformStringToEnum(Arrays.asList(TestEnum.TEST.toString()), TestEnum.class), is(Arrays.asList(TestEnum.TEST)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void transformStringToEnumFailure() {
		List<TestEnum>  enumList = ListTransformer.transformStringToEnum(Arrays.asList("TEST", "ERROR"), TestEnum.class);
		assertThat(enumList.size(), is(2));
		assertThat(enumList.get(0), is(TestEnum.TEST));
		enumList.get(1);
	}
	
	@Test
	public void transformStringToClassSuccess() {
		List<Class<?>>  actual = ListTransformer.transformStringToClass(Arrays.asList("java.lang.String", "java.util.List"));
		List<Class<?>>  expected = Arrays.asList(String.class, List.class);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void transformStringToClassFailure() {
		try {
			ListTransformer.transformStringToClass(Arrays.asList("java.lang.String", "error", "java.util.List"));
		} catch (final IllegalArgumentException ex) {
			assertThat(ex.getMessage(), is("Cannot convert 'error' to a class."));
		}
	}
}
