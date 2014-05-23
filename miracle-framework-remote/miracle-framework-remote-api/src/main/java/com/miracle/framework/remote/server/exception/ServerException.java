package com.miracle.framework.remote.server.exception;

import lombok.Getter;

import com.miracle.framework.exception.SystemException;

public final class ServerException extends SystemException {
	
	private static final long serialVersionUID = 5438288073708201395L;
	
	@Getter
	private final long messageId;
	
	public ServerException(final long messageId, final Exception cause) {
		super(cause);
		this.messageId = messageId;
	}
}
