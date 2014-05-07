package com.miracle.framework.repository.exception;

public abstract class DataIntegrityViolationException extends DataAccessException {
	
	private static final long serialVersionUID = -3705246806332035877L;
	
	private final String field;
	private final String key;
	
	protected DataIntegrityViolationException(final String field, final String key) {
		super();
		this.field = field;
		this.key = key;
	}
	
	public final String getField() {
		return field;
	}
	
	public final String getKey() {
		return key;
	}
}
