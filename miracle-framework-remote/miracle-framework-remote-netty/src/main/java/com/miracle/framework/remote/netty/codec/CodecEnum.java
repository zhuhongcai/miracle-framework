package com.miracle.framework.remote.netty.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import com.miracle.framework.remote.netty.client.JavaNettyClientChannelInitializer;
import com.miracle.framework.remote.netty.client.KryoNettyClientChannelInitializer;
import com.miracle.framework.remote.netty.client.NettyClientChannelInitializer;
import com.miracle.framework.remote.netty.server.JavaNettyServerChannelInitializer;
import com.miracle.framework.remote.netty.server.KryoNettyServerChannelInitializer;

public enum CodecEnum {
	
	Java(JavaNettyServerChannelInitializer.class, JavaNettyClientChannelInitializer.class),
	Kryo(KryoNettyServerChannelInitializer.class, KryoNettyClientChannelInitializer.class);
	
	private final Class<? extends ChannelInitializer<SocketChannel>> serverChannelInitializer;
	private final Class<? extends NettyClientChannelInitializer> clientChannelInitializer;
	
	private CodecEnum(final Class<? extends ChannelInitializer<SocketChannel>> serverChannelInitializer, final Class<? extends NettyClientChannelInitializer> clientChannelInitializer) {
		this.serverChannelInitializer = serverChannelInitializer;
		this.clientChannelInitializer = clientChannelInitializer;
	}
	
	public Class<? extends ChannelInitializer<SocketChannel>> getServerChannelInitializer() {
		return serverChannelInitializer;
	}
	
	public Class<? extends NettyClientChannelInitializer> getClientChannelInitializer() {
		return clientChannelInitializer;
	}
}
