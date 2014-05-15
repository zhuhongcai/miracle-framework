package com.miracle.framework.webmvc.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.miracle.framework.lang.BaseObject;

@Getter
@AllArgsConstructor
public final class Feedback extends BaseObject {
	
	private final String code;
	private final String message;
}
