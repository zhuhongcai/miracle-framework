package com.miracle.test.webmvc.fixture.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miracle.test.webmvc.fixture.domain.Foo;

@RestController
public class FooController {
	
	@RequestMapping(value = "fooList", method = RequestMethod.GET)
	public List<Foo> getBars() {
		return Arrays.asList(new Foo("bar1"), new Foo("bar2"));
	}
	
	@RequestMapping(value = "foo/{bar}", method = RequestMethod.GET)
	public Foo getMessage(@PathVariable String bar) {
		return new Foo(bar);
	}
	
	@RequestMapping(value = "foo", method = RequestMethod.GET)
	public Foo getMessageByparam(@RequestParam String bar) {
		return new Foo(bar);
	}
	
	@RequestMapping(value = "foo", method = RequestMethod.POST)
	public void addMessage(@RequestBody Foo foo) { }
}
