package com.miracle.framework.remote.cluster.fixture;

import com.miracle.framework.remote.cluster.AbstractRegistry;

public final class TestRegistry extends AbstractRegistry<TestClient> {
	
	@Override
	protected TestClient getNewClientInstance() {
		return new TestClient();
	}
}
