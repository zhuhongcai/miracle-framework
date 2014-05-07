package com.miracle.framework.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "test_entity")
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class TestEntity extends BaseOptimisticLockAuditable<TestAudit> {
	
	private static final long serialVersionUID = 338784112232065413L;
	
	private String name;
	
	private String extraText;
	
	public TestEntity() { }
	
	public TestEntity(final String id) {
		this(id, null);
	}
	
	public TestEntity(final String id, final String name) {
		this(id, name, null, 0L);
	}
	
	public TestEntity(final String id, final long version) {
		this(id, null, null, version);
	}
	
	public TestEntity(final String id, final String name, final String extraText, final long version) {
		setId(id);
		setVersion(version);
		this.name = name;
		this.extraText = extraText;
	}
}
