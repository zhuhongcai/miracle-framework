package com.miracle.test.webmvc.util;

import java.util.HashMap;

import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

public final class BindingResultBuilder {
	
	private BindingResultBuilder() { }
	
	public static BindingResult correctResult() {
		return new MapBindingResult(new HashMap<>(), "test");
	}
	
	public static BindingResult rejectedResult() {
		BindingResult result =  new MapBindingResult(new HashMap<>(), "test");
		result.reject("error");
		return result;
	}
	
}
