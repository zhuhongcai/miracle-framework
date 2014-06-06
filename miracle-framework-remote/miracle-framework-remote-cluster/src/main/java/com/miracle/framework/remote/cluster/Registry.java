package com.miracle.framework.remote.cluster;

import java.net.InetSocketAddress;
import java.util.List;

import com.miracle.framework.remote.client.Client;

public interface Registry<T extends Client> {
	
	void register(InetSocketAddress socketAddress);
	
	void unregister(InetSocketAddress socketAddress);
	
	List<T> getRegisteredClients();
}
