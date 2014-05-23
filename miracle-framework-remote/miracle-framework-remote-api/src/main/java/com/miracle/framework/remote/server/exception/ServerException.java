package com.miracle.framework.remote.server.exception;

import com.miracle.framework.exception.SystemException;

public final class ServerException extends SystemException {
	
	private static final long serialVersionUID = 5438288073708201395L;
	
	public ServerException(Exception cause) {
		super(cause);
	}
}
