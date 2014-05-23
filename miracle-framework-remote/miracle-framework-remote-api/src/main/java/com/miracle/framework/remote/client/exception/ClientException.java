package com.miracle.framework.remote.client.exception;

import com.miracle.framework.exception.SystemException;

public final class ClientException extends SystemException {
	
	private static final long serialVersionUID = 1400214981125931724L;
	
	public ClientException(final Exception cause) {
		super(cause);
	}
}
