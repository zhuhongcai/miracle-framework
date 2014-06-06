package com.miracle.framework.remote.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.miracle.framework.remote.client.Client;
import com.miracle.framework.remote.client.exception.ClientCloseException;
import com.miracle.framework.remote.client.exception.ClientException;
import com.miracle.framework.remote.exchange.Request;
import com.miracle.framework.remote.exchange.Response;
import com.miracle.framework.remote.netty.codec.SerializeType;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NettyClient implements Client , ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Value("${client.worker.group.threads}")
	private int workerGroupThreads;
	
	@Value("${serialize.type}")
	private SerializeType serializeType;
	
	private EventLoopGroup workerGroup;
	private Channel channel;
	
	@Override
	public void connect(final InetSocketAddress socketAddress) {
		workerGroup = new NioEventLoopGroup(workerGroupThreads);
		Bootstrap bootstrap = new Bootstrap();
		bootstrap
			.group(workerGroup)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(applicationContext.getBean(serializeType.getClientChannelInitializer()));
		channel = bootstrap.connect(socketAddress.getAddress().getHostAddress(), socketAddress.getPort()).syncUninterruptibly().channel();
	}
	
	@Override
	public Response sent(final Request request) {
		channel.writeAndFlush(request);
		return applicationContext.getBean(serializeType.getClientChannelInitializer()).getResponse(request.getMessageId());
	}
	
	@Override
	public InetSocketAddress getRemoteAddress() {
		SocketAddress remoteAddress = channel.remoteAddress();
		if (!(remoteAddress instanceof InetSocketAddress)) {
			throw new ClientException(new RuntimeException("Get remote address error, should be InetSocketAddress"));
		}
		return (InetSocketAddress) remoteAddress;
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
	
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
