package com.miracle.framework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name = "test_entity")
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Getter
@Setter
public class TestEntity extends BaseOptimisticLockAuditable<TestAudit> {
	
	private static final long serialVersionUID = 338784112232065413L;
	
	@Column
	private String name;
	
	@Column
	private String extraText;
}
