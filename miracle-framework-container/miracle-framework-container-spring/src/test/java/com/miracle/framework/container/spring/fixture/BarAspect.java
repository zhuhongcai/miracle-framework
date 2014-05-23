package com.miracle.framework.container.spring.fixture;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public final class BarAspect {
	
	@Around("com.miracle.framework.container.spring.aspect.PublicPoint.globalPonit()")
	public Object aroundLogger(final ProceedingJoinPoint pjp) throws Throwable {
		Object returnValue = pjp.proceed();
		return null == returnValue ? returnValue : returnValue.toString() + "bar";
	}
}
