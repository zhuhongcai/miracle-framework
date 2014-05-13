package com.miracle.framework.container.spring.aspect;

import org.aspectj.lang.annotation.Pointcut;

public final class PublicPoint {

	@Pointcut("execution(* com.miracle..*(..))")
	public void globalPonit() { }
}
