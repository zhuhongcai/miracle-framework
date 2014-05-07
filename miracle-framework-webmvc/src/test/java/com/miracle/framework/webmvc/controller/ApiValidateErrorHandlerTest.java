package com.miracle.framework.webmvc.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.miracle.test.webmvc.AbstractApiControllerContextTests;

public class ApiValidateErrorHandlerTest extends AbstractApiControllerContextTests {
	
	@Test
	public void invalidData() throws Exception {
		getMockMvc().perform(get("/test/api/invalidData"))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.feedbacks", hasSize(1)))
				.andExpect(jsonPath("$.feedbacks[0].code", is("error.test")));
	}
	
	@Test
	public void dataIntegrityViolation() throws Exception {
		getMockMvc().perform(get("/test/api/dataIntegrityViolation"))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.feedbacks", hasSize(1)))
				.andExpect(jsonPath("$.feedbacks[0].code", is("key")));
	}
}
