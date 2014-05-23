package com.miracle.framework.remote.netty.server;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.miracle.framework.exception.SystemException;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.server.exception.ServerException;

@Component
@Sharable
public class NettyServerDispatchHandler extends SimpleChannelInboundHandler<Request> implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Override
	protected void messageReceived(final ChannelHandlerContext ctx, final Request request) {
		Object returnValue = execute(request);
		Response response;
		if (null == returnValue || Void.class.equals(returnValue.getClass())) {
			response = new Response(request.getMessageId(), null);
		} else {
			response = new Response(request.getMessageId(), returnValue);
		}
		ctx.writeAndFlush(response);
	}
	
	private Object execute(final Request request) {
		Object result;
		try {
			Object apiInstance = applicationContext.getBean(request.getApiClass());
			Method method = apiInstance.getClass().getMethod(request.getMethod(), getParameterTypes(request.getParameters()));
			result = method.invoke(apiInstance, request.getParameters());
		} catch (final ReflectiveOperationException | SystemException ex) {
			throw new ServerException(request.getMessageId(), ex);
		}
		return result;
	}
	
	private Class<?>[] getParameterTypes(final Object[] parameters) {
		Class<?>[] result = new Class<?>[parameters.length];
		int i = 0;
		for (Object each : parameters) {
			result[i] = each.getClass();
			i++;
		}
		return result;
	}
	
	@Override
	public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
		Response response; 
		if (cause instanceof ServerException) {
			response = new Response(((ServerException) cause).getMessageId(), cause);
		} else {
			response = new Response(-1L, cause);
		}
		ctx.writeAndFlush(response);
	}
	
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
