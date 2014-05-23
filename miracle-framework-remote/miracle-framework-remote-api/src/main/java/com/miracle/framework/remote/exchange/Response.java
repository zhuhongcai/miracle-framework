package com.miracle.framework.remote.exchange;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class Response implements Serializable {
	
	private static final long serialVersionUID = 5887232731148682128L;
	
	private final Object returnValue;
	private final Throwable exception;
	
	public Response(final Object returnValue) {
		this.returnValue = returnValue;
		this.exception = null;
	}
	
	public Response(final Throwable exception) {
		this.returnValue = null;
		this.exception = exception;
	}
}
