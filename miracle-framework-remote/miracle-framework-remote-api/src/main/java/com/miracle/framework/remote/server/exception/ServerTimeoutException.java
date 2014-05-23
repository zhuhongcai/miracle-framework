package com.miracle.framework.remote.server.exception;

import com.miracle.framework.exception.SystemException;

public final class ServerTimeoutException extends SystemException {
	
	private static final long serialVersionUID = -878241930137362120L;
	
	private static final String MESSAGE = "Can't get response from server, because timeout for %s seconds.";
	
	public ServerTimeoutException(final int timeoutSeconds) {
		super(MESSAGE, timeoutSeconds);
	}
}
