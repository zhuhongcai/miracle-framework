package com.miracle.framework.repository.jpa.entity;

import com.miracle.framework.repository.entity.PersistableEntity;

public interface OptimisticLockable extends PersistableEntity {
	
	long getVersion();
	void setVersion(long version);
}
