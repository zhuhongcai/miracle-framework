package com.miracle.framework.remote;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.remote.netty.RemoteNettyTest;
import com.miracle.framework.remote.netty.client.NettyClientTest;
import com.miracle.framework.remote.netty.server.NettyServerTest;
import com.miracle.framework.remote.netty.server.ServerBootstrapTest;

@RunWith(Suite.class)
@SuiteClasses({
	RemoteNettyTest.class, 
	ServerBootstrapTest.class, 
	NettyServerTest.class, 
	NettyClientTest.class
	}
)
public class AllRemoteNettyTests { }
