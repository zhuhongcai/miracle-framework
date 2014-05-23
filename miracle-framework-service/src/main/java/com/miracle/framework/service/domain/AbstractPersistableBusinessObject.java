package com.miracle.framework.service.domain;

import org.springframework.beans.BeanUtils;

import com.miracle.framework.reflection.ReflectionUtil;
import com.miracle.framework.repository.entity.PersistableEntity;

public abstract class AbstractPersistableBusinessObject<T extends PersistableEntity> implements BusinessObject {
	
	private final Class<T> persistableEntityClass;
	
	protected AbstractPersistableBusinessObject(final Class<T> persistableEntityClass) {
		this.persistableEntityClass = persistableEntityClass;
	}
	
	public final T convertTo() {
		T result = ReflectionUtil.newInstance(persistableEntityClass);
		BeanUtils.copyProperties(this, result);
		customizeConvert(result);
		return result;
	}
	
	public final void fillFrom(final T persistableEntity) {
		BeanUtils.copyProperties(persistableEntity, this);
		customizeFill(persistableEntity);
	}
	
	protected abstract void customizeConvert(T persistableEntity);
	protected abstract void customizeFill(T persistableEntity);
}
