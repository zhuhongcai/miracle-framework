package com.miracle.framework.service.domain;

import com.miracle.framework.repository.entity.PersistableEntity;

public abstract class AbstractPersistableBusinessObjectAdapter<T extends PersistableEntity> extends AbstractPersistableBusinessObject<T> {
	
	protected AbstractPersistableBusinessObjectAdapter(Class<T> persistableEntityClass) {
		super(persistableEntityClass);
	}
	
	protected void customizeConvert( T persistableEntity) { }
	protected void customizeFill(T persistableEntity) { }
}
