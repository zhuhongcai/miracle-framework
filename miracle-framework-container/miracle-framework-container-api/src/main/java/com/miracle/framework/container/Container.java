package com.miracle.framework.container;

public interface Container {
	
	void start();
	
	void stop();
	
	Context<?> getContext();
}
