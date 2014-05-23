package com.miracle.framework.remote.client.exception;

import com.miracle.framework.exception.SystemException;

public final class CilentException extends SystemException {
	
	private static final long serialVersionUID = 1400214981125931724L;
	
	public CilentException(Exception cause) {
		super(cause);
	}
}
