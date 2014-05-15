package com.miracle.framework.repository.jpa.exception;

import java.io.Serializable;

import com.miracle.framework.repository.exception.DataAccessException;

public final class PrimaryKeyNotFoundException extends DataAccessException {

	private static final long serialVersionUID = 8387967315815101519L;
	
	public PrimaryKeyNotFoundException(final Class<?> entity, final Serializable id) {
		super(entity.getSimpleName(), id);
	}
}
