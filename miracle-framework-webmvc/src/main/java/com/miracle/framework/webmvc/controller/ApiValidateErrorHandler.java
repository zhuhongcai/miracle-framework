package com.miracle.framework.webmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.miracle.framework.repository.exception.DataIntegrityViolationException;
import com.miracle.framework.webmvc.vo.Feedback;
import com.miracle.framework.webmvc.vo.Feedbacks;

@ControllerAdvice
public class ApiValidateErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Feedbacks processInvalidData(final MethodArgumentNotValidException ex) {
		Feedbacks result = new Feedbacks();
		for (ObjectError each : ex.getBindingResult().getAllErrors()) {
			result.addFeedback(new Feedback(each.getCodes()[0], each.getDefaultMessage()));
		}
		return result;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Feedbacks processDataIntegrityViolation(final DataIntegrityViolationException ex) {
		Feedbacks result = new Feedbacks();
		result.addFeedback(new Feedback(ex.getKey(), null));
		return result;
	}
}
