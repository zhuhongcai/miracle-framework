package com.miracle.framework.remote;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.remote.cluster.RandomLoadBalanceTest;
import com.miracle.framework.remote.cluster.RegistryTest;
import com.miracle.framework.remote.cluster.RouterTest;

@RunWith(Suite.class)
@SuiteClasses({
	RegistryTest.class, 
	RouterTest.class, 
	RandomLoadBalanceTest.class
	}
)
public class AllRemoteClusterTests { }
