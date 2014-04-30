package com.miracle.framework.entity;

public interface OptimisticLockable {
	
	long getVersion();
	void setVersion(long version);
}
