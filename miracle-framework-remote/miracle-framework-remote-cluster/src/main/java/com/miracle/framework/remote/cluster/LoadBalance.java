package com.miracle.framework.remote.cluster;

import com.miracle.framework.remote.client.Client;

public interface LoadBalance {
	
	Client select();
}
