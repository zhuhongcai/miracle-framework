package com.miracle.framework.repository.jpa.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.miracle.framework.repository.jpa.exception.OptimisticLockingException;
import com.miracle.framework.repository.jpa.exception.PrimaryKeyNotFoundException;
import com.miracle.framework.repository.jpa.fixture.entity.TestEntity;
import com.miracle.framework.repository.jpa.fixture.repository.TestEntityRepository;
import com.miracle.test.repository.database.AbstractDatabaseRepositoryContextTests;

public final class BaseJpaRepositoryTest extends AbstractDatabaseRepositoryContextTests {
	
	@Resource
	private TestEntityRepository testEntityRepository;
	
	@Test
	@DatabaseSetup("Empty.xml")
	@ExpectedDatabase(value = "AfterSave.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void save() {
		testEntityRepository.save(new TestEntity(null, "test1"));
	}
	
	@Test(expected = PrimaryKeyNotFoundException.class)
	@DatabaseSetup("NormalData.xml")
	@ExpectedDatabase(value = "NormalData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void updateNotNullFailureForRecordNotFound() {
		testEntityRepository.updateNotNull(new TestEntity("notExist"));
	}
	
	@Test(expected = OptimisticLockingException.class)
	@DatabaseSetup("NormalData.xml")
	@ExpectedDatabase(value = "NormalData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void updateNotNullFailureForOptimisticLock() {
		testEntityRepository.updateNotNull(new TestEntity("1", -1));
	}
	
	@Test
	@DatabaseSetup("NormalData.xml")
	@ExpectedDatabase(value = "AfterUpdateNotNullSuccess.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void updateNotNullSuccess() {
		TestEntity actual = new TestEntity("1");
		actual.setExtraText("extraText");
		testEntityRepository.updateNotNull(actual);
	}
	
	@Test(expected = PrimaryKeyNotFoundException.class)
	@DatabaseSetup("NormalData.xml")
	@ExpectedDatabase(value = "NormalData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void deleteFailureForDataNotFound() {
		testEntityRepository.delete("NotExistId");
	}
	
	@Test
	@DatabaseSetup("NormalData.xml")
	@ExpectedDatabase(value = "AfterDeleteSuccess.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void deleteSuccess() {
		testEntityRepository.delete("1");
	}
	
	@Test(expected = PrimaryKeyNotFoundException.class)
	@DatabaseSetup("NormalData.xml")
	public void findOneFailureForDataNotFound() {
		testEntityRepository.findOne("NotExistId");
	}
	
	@Test
	@DatabaseSetup("NormalData.xml")
	public void findOneSuccess() {
		assertThat(testEntityRepository.findOne("1"), is(new TestEntity("1", "test1", "1", 0L)));
		assertThat(testEntityRepository.findOne("2"), is(new TestEntity("2", "test2", "2", 10L)));
	}
	
	@Test
	@DatabaseSetup("NormalData.xml")
	public void findAll() {
		Page<TestEntity> testEntities = testEntityRepository.findAll(new PageRequest(0, 10));
		assertThat(testEntities.getTotalElements(), is(2L));
		assertThat(testEntities.getTotalPages(), is(1));
		assertThat(testEntities.getContent().size(), is(2));
		assertThat(testEntities.getContent().get(0), is(new TestEntity("1", "test1", "1", 0L)));
		assertThat(testEntities.getContent().get(1), is(new TestEntity("2", "test2", "2", 10L)));
		
		testEntities = testEntityRepository.findAll(new PageRequest(0, 1));
		assertThat(testEntities.getTotalElements(), is(2L));
		assertThat(testEntities.getTotalPages(), is(2));
		assertThat(testEntities.getContent().size(), is(1));
		assertThat(testEntities.getContent().get(0), is(new TestEntity("1", "test1", "1", 0L)));
		
		testEntities = testEntityRepository.findAll(new PageRequest(1, 1));
		assertThat(testEntities.getTotalElements(), is(2L));
		assertThat(testEntities.getTotalPages(), is(2));
		assertThat(testEntities.getContent().size(), is(1));
		assertThat(testEntities.getContent().get(0), is(new TestEntity("2", "test2", "2", 10L)));
	}
}
