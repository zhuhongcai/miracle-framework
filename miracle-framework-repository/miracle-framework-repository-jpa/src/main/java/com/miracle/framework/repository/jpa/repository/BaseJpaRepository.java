package com.miracle.framework.repository.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<ENTITY, ID extends Serializable> extends JpaRepository<ENTITY, ID> {
	
	ENTITY updateNotNull(ENTITY entity);
}
