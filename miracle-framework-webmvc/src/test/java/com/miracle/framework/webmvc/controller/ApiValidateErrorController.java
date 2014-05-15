package com.miracle.framework.webmvc.controller;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miracle.framework.repository.jpa.exception.DataIntegrityViolationException;
import com.miracle.test.webmvc.util.BindingResultBuilder;

@Controller
@RequestMapping("test/api")
public class ApiValidateErrorController {
	
	@RequestMapping("invalidData")
	@ResponseBody
	public void invalidData() throws MethodArgumentNotValidException, NoSuchMethodException {
		throw new MethodArgumentNotValidException(
				new MethodParameter(Object.class.getMethod("hashCode"), 1), 
				BindingResultBuilder.rejectedResult());
	}
	
	@RequestMapping("dataIntegrityViolation")
	@ResponseBody
	public void dataIntegrityViolation() {
		throw new TestDataIntegrityViolationException();
	}
	
	private class TestDataIntegrityViolationException extends DataIntegrityViolationException {
		
		private static final long serialVersionUID = -7865497585042528165L;
		
		protected TestDataIntegrityViolationException() {
			super("field", "key");
		}
	}
}
