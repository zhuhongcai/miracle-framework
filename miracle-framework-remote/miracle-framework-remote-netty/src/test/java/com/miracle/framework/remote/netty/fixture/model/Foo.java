package com.miracle.framework.remote.netty.fixture.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Foo implements Serializable {
	
	private static final long serialVersionUID = 2213133522077991039L;
	
	private String bar;
}
