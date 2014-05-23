package com.miracle.framework.remote.netty.client;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;

@Component
@Sharable
public class NettyClientDispatchHandler extends SimpleChannelInboundHandler<Response> {
	
	private final ConcurrentHashMap<Long, BlockingQueue<Response>> responseMap = new ConcurrentHashMap<>();
	
	@Override
	public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
		if (msg instanceof Request) {
			Request request = (Request) msg;
			responseMap.putIfAbsent(request.getMessageId(), new LinkedBlockingQueue<Response>(1));
		}
		super.write(ctx, msg, promise);
	}
	
	@Override
	protected void messageReceived(final ChannelHandlerContext ctx, final Response response) throws Exception {
		BlockingQueue<Response> queue = responseMap.get(response.getMessageId());
		queue.add(response);
	}
	
	public BlockingQueue<Response> getResponseQueue(final long messageId) {
		responseMap.putIfAbsent(messageId, new LinkedBlockingQueue<Response>(1));
		return responseMap.get(messageId);
	}
}
