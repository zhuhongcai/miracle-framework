package com.miracle.framework.repository.jpa.entity;

public interface OptimisticLockable {
	
	long getVersion();
	void setVersion(long version);
}
