package com.miracle.framework.reflection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public final class ReflectionUtil {
	
	private ReflectionUtil() { }
	
	public static <T> T newInstance(final Class<T> clazz) {
		T result;
		try {
			result = clazz.newInstance();
		} catch (final ReflectiveOperationException e) {
			throw new ReflectionException(e.getMessage());
		}
		return result;
	}
	
	public static Collection<Field> getAllNonStaticFields(final Class<?> clazz) {
		Collection<Field> result = new LinkedHashSet<>();
		result.addAll(getNonStaticFields(ClassUtils.getAllSuperclasses(clazz)));
		result.addAll(getNonStaticFields(clazz));
		return result;
	}
	
	private static Collection<Field> getNonStaticFields(final Collection<Class<?>> classes) {
		Collection<Field> result = new ArrayList<>();
		for (Class<?> each : classes) {
			result.addAll(getNonStaticFields(each));
		}
		return result;
	}
	
	private static Collection<Field> getNonStaticFields(final Class<?> clazz) {
		Collection<Field> result = new ArrayList<>();
		for (Field each : clazz.getDeclaredFields()) {
			if (!Modifier.isStatic(each.getModifiers())) {
				result.add(each);
			}
		}
		return result;
	}
	
	public static String[] getNullPropertyNames(final Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();
		Set<String> nullValueFieldNames = new HashSet<>();
		for (PropertyDescriptor each : propertyDescriptors) {
			Object value = src.getPropertyValue(each.getName());
			if (null == value) {
				nullValueFieldNames.add(each.getName());
			}
		}
		return nullValueFieldNames.toArray(new String[nullValueFieldNames.size()]);
	}
}
