package com.miracle.framework.remote.netty.server;

import org.junit.Test;

public final class ServerBootstrapTest {
	
	@Test
	public void main() {
		ServerBootstrap.main(null);
		ServerBootstrap.stopServer();
	}
}
