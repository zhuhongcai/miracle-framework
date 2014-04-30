package com.miracle.framework.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miracle.framework.repository.exception.PrimaryKeyNotFoundException;

@Controller
@RequestMapping("test/api")
public class ApiDataNotFoundErrorController {
	
	@RequestMapping("dataNotFound")
	@ResponseBody
	public void invalidData() {
		throw new PrimaryKeyNotFoundException(Object.class, 1);
	}
	
}
