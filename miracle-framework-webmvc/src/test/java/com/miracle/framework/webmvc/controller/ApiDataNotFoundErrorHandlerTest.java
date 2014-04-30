package com.miracle.framework.webmvc.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.miracle.test.webmvc.ApiControllerBaseTest;

public class ApiDataNotFoundErrorHandlerTest extends ApiControllerBaseTest {
	
	@Test
	public void dataNotFound() throws Exception {
		getMockMvc().perform(get("/test/api/dataNotFound"))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.feedbacks", hasSize(1)))
				.andExpect(jsonPath("$.feedbacks[0].code", is("com.miraclesea.framework.dao.exception.PrimaryKeyNotFoundException")))
				.andExpect(jsonPath("$.feedbacks[0].message", is("Object,1")));
	}
}
