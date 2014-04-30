package com.miracle.framework.aspect;

import org.aspectj.lang.annotation.Pointcut;

public final class PublicPoint {

	/**
	 * 全局切面.
	 * 即com.miracle包下的任意方法.
	 * 
	 * execution内的表达式用于匹配方法.
	 * 完整的表达式为:
	 * execution(修饰符? 返回类型　声明类型?　方法名称(参数类型)　异常类型?)
	 * 例如:
	 * execution(public set*(java.lang.String,..) throws java.io.IOException)
	 * 表示执行public的, 以set开头的，至少有一个参数, 且第一个参数类型是String的方法, 并且该方法抛出IOException
	 */
	@Pointcut("execution(* com.miracle..*(..))")
	public void globalPonit() { }
}
