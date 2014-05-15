package com.miracle.test.webmvc;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(locations = {
	"classpath:META-INF/spring/internal/root/testWebmvcContext.xml", 
	"classpath:META-INF/springmvc/webContext.xml"
})
@WebAppConfiguration
public abstract class AbstractApiControllerContextTests extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext; 
	
	private MockMvc mockMvc;
	
	@Before
	public final void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MockitoAnnotations.initMocks(this);
	}
	
	protected final MockMvc getMockMvc() {
		return mockMvc;
	}
	
	protected final String toJson(final Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (final JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
	}
}
