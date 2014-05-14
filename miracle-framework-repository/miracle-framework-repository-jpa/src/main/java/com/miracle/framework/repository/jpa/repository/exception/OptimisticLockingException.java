package com.miracle.framework.repository.jpa.repository.exception;

import com.miracle.framework.repository.jpa.entity.OptimisticLockable;

public final class OptimisticLockingException extends DataIntegrityViolationException {
	
	private static final long serialVersionUID = 8521143237454274770L;
	
	private static final String FIELD_NAME = "version";
	private static final String KEY = "OptimisticLock.%s.version";
	
	public OptimisticLockingException(final OptimisticLockable entity) {
		super(FIELD_NAME, String.format(KEY, entity.getClass().getSimpleName()));
	}
}
