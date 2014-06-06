package com.miracle.framework.remote.cluster;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.miracle.framework.remote.client.Client;

public abstract class AbstractRegistry<T extends Client> implements Registry<T> {
	
	private final List<T> clients = new ArrayList<>();
	
	@Override
	public void register(final InetSocketAddress socketAddress) {
		if (null != findClient(socketAddress)) {
			return;
		}
		T client = getNewClientInstance();
		client.connect(socketAddress);
		clients.add(client);
	}
	
	@Override
	public void unregister(final InetSocketAddress socketAddress) {
		T client = findClient(socketAddress);
		if (null == client) {
			return;
		}
		clients.remove(client);
	}
	
	private T findClient(final InetSocketAddress socketAddress) {
		for (T each : clients) {
			if (each.getRemoteAddress().equals(socketAddress)) {
				return each;
			}
		}
		return null;
	}
	
	protected abstract T getNewClientInstance();
	
	@Override
	public List<T> getRegisteredClients() {
		return Collections.unmodifiableList(clients);
	}
}
