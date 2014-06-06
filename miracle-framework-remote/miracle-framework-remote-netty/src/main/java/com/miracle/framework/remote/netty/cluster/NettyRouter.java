package com.miracle.framework.remote.netty.cluster;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.miracle.framework.remote.cluster.AbstractRouter;
import com.miracle.framework.remote.cluster.LoadBalance;
import com.miracle.framework.remote.cluster.RandomLoadBalance;

@Component
public class NettyRouter extends AbstractRouter {
	
	@Resource
	private NettyRegistry registry;
	
	private LoadBalance loadBalance;
	
	@PostConstruct
	public void init() {
		loadBalance = new RandomLoadBalance(registry);
	}
	
	@Override
	protected LoadBalance getLoadBalance() {
		return loadBalance;
	}
}
