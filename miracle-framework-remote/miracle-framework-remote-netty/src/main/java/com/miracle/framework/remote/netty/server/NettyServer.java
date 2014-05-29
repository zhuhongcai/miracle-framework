package com.miracle.framework.remote.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.miracle.framework.remote.netty.codec.CodecEnum;
import com.miracle.framework.remote.server.Server;
import com.miracle.framework.remote.server.exception.ServerException;
import com.miracle.framework.remote.server.exception.ServerStopException;

@Component
public class NettyServer implements Server, ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Value("${server.boss.group.threads}")
	private int bossGroupThreads;
	
	@Value("${server.worker.group.threads}")
	private int workerGroupThreads;
	
	@Value("${server.backlog.size}")
	private int backlogSize;
	
	@Value("${serialize.type}")
	private CodecEnum codec;
	
	private Channel channel;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	@Override
	public void start(final int port) {
		bossGroup = new NioEventLoopGroup(bossGroupThreads);
		workerGroup = new NioEventLoopGroup(workerGroupThreads);
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap
			.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, backlogSize)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childOption(ChannelOption.TCP_NODELAY, true)
			.childHandler(applicationContext.getBean(codec.getServerChannelInitializer()));
		try {
			channel = serverBootstrap.bind(port).sync().channel();
		} catch (final InterruptedException ex) {
			throw new ServerException(Server.SYSTEM_MESSAGE_ID, ex);
		}
	}
	
	@Override
	public void stop() {
		if (null == channel) {
			throw new ServerStopException();
		}
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		channel.closeFuture().syncUninterruptibly();
		bossGroup = null;
		workerGroup = null;
		channel = null;
	}
	
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
