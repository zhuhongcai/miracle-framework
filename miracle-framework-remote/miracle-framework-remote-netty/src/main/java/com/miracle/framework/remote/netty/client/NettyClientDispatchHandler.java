package com.miracle.framework.remote.netty.client;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.miracle.framework.remote.exchange.Response;

@Component
@Sharable
public class NettyClientDispatchHandler extends SimpleChannelInboundHandler<Response> {
	
	private final BlockingQueue<Response> responseQueue = new LinkedBlockingQueue<Response>();
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Response response) throws Exception {
		responseQueue.add(response);
	}
	
	public BlockingQueue<Response> getResponseQueue() {
		return responseQueue;
	}
}
