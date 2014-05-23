package com.miracle.framework.remote.netty.server;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.miracle.framework.container.spring.SpringContainer;

public final class ServerBootstrap {
	
	private static AbstractApplicationContext ctx;
	
	public static void main(String[] args) {
		SpringContainer container = startContainer();
		ctx = container.getContext().get();
		startServer();
	}
	
	private static SpringContainer startContainer() {
		SpringContainer container = new SpringContainer();
		container.start();
		return container;
	}
	
	private static void startServer() {
		Object port = ctx.getBean(PropertySourcesPlaceholderConfigurer.class).getAppliedPropertySources().get("localProperties").getProperty("server.port");
		ctx.getBean(NettyServer.class).start(Integer.parseInt(port.toString()));
	}
	
	public static void stopServer() {
		ctx.getBean(NettyServer.class).stop();
	}
}
