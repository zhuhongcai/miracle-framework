package com.miracle.framework.remote.cluster;

import java.util.List;
import java.util.Random;

import com.miracle.framework.remote.client.Client;

public class RandomLoadBalance implements LoadBalance {
	
	private final Registry<? extends Client> registry;
	
	public RandomLoadBalance(final Registry<? extends Client> registry) {
		this.registry = registry;
	}
	
	@Override
	public Client select() {
		List<? extends Client> registeredClients = registry.getRegisteredClients();
		int selected = new Random().nextInt(registeredClients.size());
		return registeredClients.get(selected);
	}
}
