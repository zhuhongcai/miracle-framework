package com.miracle.framework.repository.exception;

import com.miracle.framework.exception.UserException;

public abstract class DataAccessException extends UserException {
	
	private static final long serialVersionUID = -2315888359085614417L;
	
	protected DataAccessException(final Object... args) {
		super(args);
	}

}
