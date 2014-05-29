package com.miracle.framework.remote.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.miracle.framework.remote.exchange.Response;

@Component
public class NettyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	@Resource
	private NettyClientDispatchHandler clientDispatchHandler;
	
	@Override
	protected void initChannel(final SocketChannel ch) throws Exception {
		ch.pipeline().addLast(clientDispatchHandler);
	}
	
	public Response getResponse(final long messageId) {
		return clientDispatchHandler.getResponse(messageId);
	}
}
