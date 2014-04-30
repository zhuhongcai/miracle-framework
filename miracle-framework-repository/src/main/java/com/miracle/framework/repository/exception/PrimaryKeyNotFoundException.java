package com.miracle.framework.repository.exception;

import java.io.Serializable;

public final class PrimaryKeyNotFoundException extends DataAccessException {

	private static final long serialVersionUID = 8387967315815101519L;
	
	public PrimaryKeyNotFoundException(final Class<?> entity, final Serializable id) {
		super(entity.getSimpleName(), id);
	}
}
