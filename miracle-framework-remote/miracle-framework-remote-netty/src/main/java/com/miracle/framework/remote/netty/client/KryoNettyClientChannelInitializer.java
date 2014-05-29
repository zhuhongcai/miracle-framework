package com.miracle.framework.remote.netty.client;

import io.netty.channel.socket.SocketChannel;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.miracle.framework.remote.netty.codec.KryoDecoder;
import com.miracle.framework.remote.netty.codec.KryoEncoder;
import com.miracle.framework.remote.netty.codec.KryoPool;

@Component
public class KryoNettyClientChannelInitializer extends NettyClientChannelInitializer {
	
	
	@Resource
	private KryoPool kryoSerializationFactory;
	
	@Override
	protected void initChannel(final SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new KryoEncoder(kryoSerializationFactory));
		ch.pipeline().addLast(new KryoDecoder(kryoSerializationFactory));
		super.initChannel(ch);
	}
}
