package com.miracle.framework.repository.jpa.repository.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.miracle.framework.common.util.reflection.ReflectionUtil;
import com.miracle.framework.repository.jpa.entity.OptimisticLockable;
import com.miracle.framework.repository.jpa.repository.BaseJpaRepository;
import com.miracle.framework.repository.jpa.repository.exception.OptimisticLockingException;
import com.miracle.framework.repository.jpa.repository.exception.PrimaryKeyNotFoundException;

@Transactional(readOnly = true)
public final class BaseJpaRepositoryImpl<E extends Persistable<I>, I extends Serializable> extends SimpleJpaRepository<E, I> implements BaseJpaRepository<E, I> {

	private final EntityManager entityManager;
	private final Class<E> domainClass;
	
	public BaseJpaRepositoryImpl(final Class<E> domainClass, final EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
		this.domainClass = domainClass;
	}
	
	@Override
	@Transactional
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
	@Transactional
	public void delete(final I id) {
		findOne(id);
		super.delete(id);
	}
}
