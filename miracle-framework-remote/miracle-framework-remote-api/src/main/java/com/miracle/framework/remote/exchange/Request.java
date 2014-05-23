package com.miracle.framework.remote.exchange;

import java.io.Serializable;

import lombok.Getter;

@Getter
public final class Request implements Serializable {
	
	private static final long serialVersionUID = 2750646443189480771L;
	
	private final Class<?> apiClass;
	private final String method;
	private final Object[] parameters;
	
	public Request(Class<?> apiClass, String method, Object... parameters) {
		this.apiClass = apiClass;
		this.method = method;
		this.parameters = parameters;
	}
}
