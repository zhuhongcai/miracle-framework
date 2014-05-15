package com.miracle.test.webmvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.miracle.test.webmvc.fixture.domain.Foo;

public final class AbstractApiControllerContextTestsTest extends AbstractApiControllerContextTests {
	
	@Test
	public void getAllMessages() throws Exception {
		getMockMvc().perform(get("/fooList"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$[0].bar", is("bar1")))
				.andExpect(jsonPath("$[1].bar", is("bar2")));
	}
	
	@Test
	public void getMessage() throws Exception {
		getMockMvc().perform(get("/foo/bar1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.bar", is("bar1")));
	}
	
	@Test
	public void getMessageByParam() throws Exception {
		getMockMvc().perform(get("/foo").param("bar", "bar2"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.bar", is("bar2")));
	}
	
	@Test
	public void addMessageFailure() throws Exception {
		getMockMvc().perform(post("/foo").content(toJson(123)).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addMessageSucess() throws Exception {
		getMockMvc().perform(post("/foo").content(toJson(new Foo("bar3"))).contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
				.andExpect(status().isOk());
	}
}
