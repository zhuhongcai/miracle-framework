package com.miracle.framework.remote.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.miracle.framework.remote.netty.codec.KryoDecoder;
import com.miracle.framework.remote.netty.codec.KryoEncoder;
import com.miracle.framework.remote.netty.codec.KryoPool;

@Component
public class KryoNettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	@Resource
	private NettyServerDispatchHandler serverDispatchHandler;
	
	@Resource
	private KryoPool kryoSerializationFactory;
	
	@Override
	protected void initChannel(final SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new KryoEncoder(kryoSerializationFactory));
		ch.pipeline().addLast(new KryoDecoder(kryoSerializationFactory));
		ch.pipeline().addLast(serverDispatchHandler);
	}
}
