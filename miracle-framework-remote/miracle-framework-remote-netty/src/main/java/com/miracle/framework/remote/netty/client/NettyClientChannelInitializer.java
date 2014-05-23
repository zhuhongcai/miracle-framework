package com.miracle.framework.remote.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.miracle.framework.remote.exchange.Response;

@Component
public class NettyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	@Resource
	private NettyClientDispatchHandler clientDispatchHandler;
	
	@Override
	protected void initChannel(final SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new ObjectEncoder());
		ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
		ch.pipeline().addLast(clientDispatchHandler);
	}
	
	public Response getResponse(final long messageId) {
		return clientDispatchHandler.getResponse(messageId);
	}
}
