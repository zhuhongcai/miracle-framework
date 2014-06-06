package com.miracle.framework.remote.netty.cluster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.miracle.framework.remote.cluster.AbstractRegistry;
import com.miracle.framework.remote.netty.client.NettyClient;

@Component
public class NettyRegistry extends AbstractRegistry<NettyClient> implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Override
	protected NettyClient getNewClientInstance() {
		return applicationContext.getBean(NettyClient.class);
	}
	
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
