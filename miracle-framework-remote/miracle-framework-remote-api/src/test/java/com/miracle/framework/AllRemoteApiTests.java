package com.miracle.framework;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.miracle.framework.remote.client.exception.ClientCloseExceptionTest;
import com.miracle.framework.remote.client.exception.ClientExceptionTest;
import com.miracle.framework.remote.exchange.RequestTest;
import com.miracle.framework.remote.exchange.ResponseTest;
import com.miracle.framework.remote.server.exception.ServerExceptionTest;
import com.miracle.framework.remote.server.exception.ServerStopExceptionTest;

@RunWith(Suite.class)
@SuiteClasses({
	RequestTest.class, 
	ResponseTest.class, 
	ServerExceptionTest.class, 
	ServerStopExceptionTest.class, 
	ClientExceptionTest.class, 
	ClientCloseExceptionTest.class
	}
)
public class AllRemoteApiTests { }
