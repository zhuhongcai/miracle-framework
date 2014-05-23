package com.miracle.framework.repository.jpa.entity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.miracle.framework.repository.jpa.fixture.entity.TestEntity;

public final class BasePersistableTest {
	
	@Test
	public void isNew() {
		TestEntity testEntity = new TestEntity();
		assertTrue(testEntity.isNew());
	}
	
	@Test
	public void isNotNew() {
		TestEntity testEntity = new TestEntity();
		testEntity.setId("id");
		assertFalse(testEntity.isNew());
	}
}
