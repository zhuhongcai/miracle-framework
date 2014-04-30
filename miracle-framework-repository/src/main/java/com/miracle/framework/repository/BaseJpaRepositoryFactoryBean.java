package com.miracle.framework.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.miracle.framework.repository.impl.BaseJpaRepositoryImpl;

public final class BaseJpaRepositoryFactoryBean<R extends JpaRepository<T , I>, T extends AbstractPersistable<I>, I extends Serializable> 
																extends JpaRepositoryFactoryBean<R, T, I> {
	
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(final EntityManager entityManager) {
		return new BaseRepositoryFactory<T, I>(entityManager);
	}
	
	private static class BaseRepositoryFactory<T extends AbstractPersistable<I>, I extends Serializable> extends JpaRepositoryFactory {
		
		private EntityManager entityManager;
		
		public BaseRepositoryFactory(final EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}
		
		@Override
		protected Object getTargetRepository(final RepositoryMetadata metadata) {
			@SuppressWarnings("unchecked")
			Class<T> domainType = (Class<T>) metadata.getDomainType();
			return new BaseJpaRepositoryImpl<T, I>(domainType, entityManager);
		}
		
		@Override
		protected Class<?> getRepositoryBaseClass(final RepositoryMetadata metadata) {
			return BaseJpaRepository.class;
		}
	}
}
