package com.miracle.framework.repository.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.miracle.framework.entity.OptimisticLockable;
import com.miracle.framework.reflection.ReflectionUtil;
import com.miracle.framework.repository.BaseJpaRepository;
import com.miracle.framework.repository.exception.OptimisticLockingException;
import com.miracle.framework.repository.exception.PrimaryKeyNotFoundException;

public final class BaseJpaRepositoryImpl<E extends Persistable<I>, I extends Serializable> extends SimpleJpaRepository<E, I> implements BaseJpaRepository<E, I> {

	private final EntityManager entityManager;
	private final Class<E> domainClass;
	
	public BaseJpaRepositoryImpl(final Class<E> domainClass, final EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
		this.domainClass = domainClass;
	}
	
	@Override
	public E updateNotNull(final E entity) {
		E entityFromDb = findOne(entity.getId());
		if (entity instanceof OptimisticLockable && entityFromDb instanceof OptimisticLockable) {
			validateOptimisticLock((OptimisticLockable) entity, (OptimisticLockable) entityFromDb);
		}
		BeanUtils.copyProperties(entity, entityFromDb, ReflectionUtil.getNullPropertyNames(entity));
		return entityManager.merge(entityFromDb);
	}
	
	private void validateOptimisticLock(final OptimisticLockable entity, final OptimisticLockable entityFromDb) {
		if (entityFromDb.getVersion() > entity.getVersion()) {
			throw new OptimisticLockingException(entityFromDb);
		}
	}

	@Override
	public E findOne(final I id) {
		E result = super.findOne(id);
		if (null == result) {
			throw new PrimaryKeyNotFoundException(domainClass, id);
		}
		return result;
	}
	
	@Override
	public void delete(final I id) {
		findOne(id);
		super.delete(id);
	}
}
