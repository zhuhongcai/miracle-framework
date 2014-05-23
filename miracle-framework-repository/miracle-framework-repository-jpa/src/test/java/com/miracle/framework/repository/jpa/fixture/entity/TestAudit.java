package com.miracle.framework.repository.jpa.fixture.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.miracle.framework.repository.jpa.entity.BaseOptimisticLockAuditable;

@Entity
@Table(name = "test_audit")
public class TestAudit extends BaseOptimisticLockAuditable<TestAudit> {

	private static final long serialVersionUID = -7822403490177737434L;

}
