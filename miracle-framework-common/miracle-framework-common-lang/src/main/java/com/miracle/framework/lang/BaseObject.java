package com.miracle.framework.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class BaseObject {
	
	private Collection<String> excludeFields = new HashSet<>();
	
	public BaseObject() {
		this("excludeFields");
	}
	
	public BaseObject(final String... excludeFields) {
		this.excludeFields.addAll(Arrays.asList(excludeFields));
		this.excludeFields.add("excludeFields");
	}
	
	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, excludeFields);
	}
	
	@Override
	public final boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, excludeFields);
	}
	
	@Override
	public final String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, excludeFields);
	}
}
