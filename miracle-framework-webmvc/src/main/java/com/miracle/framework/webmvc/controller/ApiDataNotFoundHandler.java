package com.miracle.framework.webmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.miracle.framework.repository.exception.PrimaryKeyNotFoundException;
import com.miracle.framework.webmvc.vo.Feedback;
import com.miracle.framework.webmvc.vo.Feedbacks;

@ControllerAdvice
public class ApiDataNotFoundHandler {
	
	@ExceptionHandler(PrimaryKeyNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Feedbacks processPrimaryKeyNotFound(final PrimaryKeyNotFoundException ex) {
		Feedbacks result = new Feedbacks();
		result.addFeedback(new Feedback(ex.getErrorCode(), ex.getArguments()));
		return result;
	}
}
