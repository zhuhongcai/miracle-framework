package com.miracle.framework.remote.netty.client;

import static java.util.concurrent.TimeUnit.SECONDS;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.miracle.framework.remote.client.Client;
import com.miracle.framework.remote.client.exception.CilentException;
import com.miracle.framework.remote.client.exception.ClientCloseException;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.server.exception.ServerTimeoutException;

@Component
public class NettyClient implements Client {
	
	@Value("${client.worker.group.threads}")
	private int workerGroupThreads;
	
	@Value("${client.timeout.seconds}")
	private int timeoutSeconds;
	
	@Resource
	private NettyClientChannelInitializer cilentChannelInitializer;
	
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
			.handler(cilentChannelInitializer);
		channel = bootstrap.connect(ip, port).syncUninterruptibly().channel();
	}
	
	@Override
	public Response sent(final Request request) {
		channel.writeAndFlush(request);
		BlockingQueue<Response> responseQueue = cilentChannelInitializer.getResponseQueue(request.getMessageId());
		Response result;
		try {
			result = responseQueue.poll(timeoutSeconds, SECONDS);
		} catch (InterruptedException ex) {
			throw new CilentException(ex);
		}
		if (null == result) {
			throw new ServerTimeoutException(timeoutSeconds);
		}
		return result;
	}
	
	@Override
	public void close() {
		if (null == channel || null == workerGroup) {
			throw new ClientCloseException();
		}
		workerGroup.shutdownGracefully();
		channel.closeFuture().syncUninterruptibly();
		workerGroup = null;
		channel = null;
	}
}
