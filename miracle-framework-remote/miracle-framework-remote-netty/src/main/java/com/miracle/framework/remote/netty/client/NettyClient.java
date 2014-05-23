package com.miracle.framework.remote.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.miracle.framework.remote.client.Client;
import com.miracle.framework.remote.client.exception.ClientCloseException;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;

@Component
public class NettyClient implements Client {
	
	@Value("${client.worker.group.threads}")
	private int workerGroupThreads;
	
	@Resource
	private NettyClientChannelInitializer clientChannelInitializer;
	
	private EventLoopGroup workerGroup;
	private Channel channel;
	
	@Override
	public void connect(final String ip, final int port) {
		workerGroup = new NioEventLoopGroup(workerGroupThreads);
		Bootstrap bootstrap = new Bootstrap();
		bootstrap
			.group(workerGroup)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(clientChannelInitializer);
		channel = bootstrap.connect(ip, port).syncUninterruptibly().channel();
	}
	
	@Override
	public Response sent(final Request request) {
		channel.writeAndFlush(request);
		return clientChannelInitializer.getResponse(request.getMessageId());
	}
	
	@Override
	public void close() {
		if (null == channel) {
			throw new ClientCloseException();
		}
		workerGroup.shutdownGracefully();
		channel.closeFuture().syncUninterruptibly();
		workerGroup = null;
		channel = null;
	}
}
