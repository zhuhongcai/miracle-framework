package com.miracle.test.service.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.util.ReflectionTestUtils;

public final class InjectUtil {
	
	private InjectUtil() { }
	
	public static void injectTargetForProxyBean(final Object testUnitInstance) throws IllegalAccessException {
		for (Field injectMocksField : getInjectMocksFields(testUnitInstance.getClass())) {
			for (Field mockField : getMockFields(testUnitInstance.getClass())) {
				ReflectionTestUtils.setField(getTarget(injectMocksField.get(testUnitInstance)), mockField.getName(), mockField.get(testUnitInstance));
			}
		}
	}
	
	private static Collection<Field> getInjectMocksFields(final Class<?> clazz) {
		return getFieldsByAnnotation(clazz, InjectMocks.class);
	}
	
	private static Collection<Field> getMockFields(final Class<?> clazz) {
		return getFieldsByAnnotation(clazz, Mock.class);
	}
	
	private static Collection<Field> getFieldsByAnnotation(final Class<?> clazz, final Class<? extends Annotation> annotationClass) {
		Field[] fields = clazz.getDeclaredFields();
		List<Field> result = new ArrayList<>(fields.length);
		for (Field each : fields) {
			if (null != each.getAnnotation(annotationClass)) {
				each.setAccessible(true);
				result.add(each);
			}
		}
		return result;
	}
	
	private static Object getTarget(final Object proxyObj) {
		if (AopUtils.isAopProxy(proxyObj) && proxyObj instanceof Advised) {
			try {
				return ((Advised) proxyObj).getTargetSource().getTarget();
			} catch (final Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return proxyObj;
	}
}
