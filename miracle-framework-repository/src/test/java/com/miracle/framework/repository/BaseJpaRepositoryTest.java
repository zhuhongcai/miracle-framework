package com.miracle.framework.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBeanByType;

import com.miracle.framework.entity.TestEntity;
import com.miracle.framework.repository.exception.OptimisticLockingException;
import com.miracle.framework.repository.exception.PrimaryKeyNotFoundException;
import com.miracle.test.repository.database.DatabaseRepositoryBaseTest;

@DataSet
public final class BaseJpaRepositoryTest extends DatabaseRepositoryBaseTest {
	
	@SpringBeanByType
	private TestEntityRepository testEntityRepository;
	
	@Test
	public void save() {
		TestEntity actual = new TestEntity();
		actual.setName("test3");
		testEntityRepository.save(actual);
		assertThat(testEntityRepository.count(), is(3L));
		for (TestEntity expected : testEntityRepository.findAll()) {
			if ("1".equals(expected.getId()) || "2".equals(expected.getId())) {
				continue;
			}
			assertReflectionEquals(expected, actual);
		}
		
	}
	
	@Test(expected = PrimaryKeyNotFoundException.class)
	@Transactional(TransactionMode.ROLLBACK)
	public void updateNotNullFailureForRecordNotFound() {
		TestEntity actual = new TestEntity();
		actual.setId("notExist");
		testEntityRepository.updateNotNull(actual);
	}
	
	@Test(expected = OptimisticLockingException.class)
	@Transactional(TransactionMode.ROLLBACK)
	public void updateNotNullFailureForOptimisticLock() {
		TestEntity actual = new TestEntity();
		actual.setId("1");
		actual.setVersion(-1L);
		testEntityRepository.updateNotNull(actual);
	}
	
	@Test
	public void updateNotNullSuccess() {
		TestEntity actual = new TestEntity();
		actual.setId("1");
		actual.setExtraText("extraText");
		testEntityRepository.updateNotNull(actual);
		assertThat(testEntityRepository.count(), is(2L));
		assertReflectionEquals(testEntityRepository.findAll().get(0), buildTestEntity("1", "test1", "extraText", 1L));
	}
	
	@Test(expected = PrimaryKeyNotFoundException.class)
	@Transactional(TransactionMode.ROLLBACK)
	public void deleteFailureForDataNotFound() {
		testEntityRepository.delete("NotExistId");
	}
	
	@Test
	@ExpectedDataSet
	public void deleteSuccess() {
		testEntityRepository.delete("1");
	}
	
	@Test(expected = PrimaryKeyNotFoundException.class)
	@Transactional(TransactionMode.ROLLBACK)
	public void findOneFailureForDataNotFound() {
		testEntityRepository.findOne("NotExistId");
	}
	
	@Test
	public void findOneSuccess() {
		assertReflectionEquals(testEntityRepository.findOne("1"), buildTestEntity("1", "test1", null, 0L));
		assertReflectionEquals(testEntityRepository.findOne("2"), buildTestEntity("2", "test2", null, 10L));
	}
	
	@Test
	public void findAll() {
		Page<TestEntity> testEntities = testEntityRepository.findAll(new PageRequest(0, 10));
		assertThat(testEntities.getTotalElements(), is(2L));
		assertThat(testEntities.getTotalPages(), is(1));
		assertThat(testEntities.getContent().size(), is(2));
		assertReflectionEquals(testEntities.getContent().get(0), buildTestEntity("1", "test1", null, 0L));
		assertReflectionEquals(testEntities.getContent().get(1), buildTestEntity("2", "test2", null, 10L));
		
		testEntities = testEntityRepository.findAll(new PageRequest(0, 1));
		assertThat(testEntities.getTotalElements(), is(2L));
		assertThat(testEntities.getTotalPages(), is(2));
		assertThat(testEntities.getContent().size(), is(1));
		assertReflectionEquals(testEntities.getContent().get(0), buildTestEntity("1", "test1", null, 0L));
		
		testEntities = testEntityRepository.findAll(new PageRequest(1, 1));
		assertThat(testEntities.getTotalElements(), is(2L));
		assertThat(testEntities.getTotalPages(), is(2));
		assertThat(testEntities.getContent().size(), is(1));
		assertReflectionEquals(testEntities.getContent().get(0), buildTestEntity("2", "test2", null, 10L));
	}
	
	private TestEntity buildTestEntity(final String id, final String name, final String extraText, final long version) {
		TestEntity result = new TestEntity();
		result.setId(id);
		result.setName(name);
		result.setExtraText(extraText);
		result.setVersion(version);
		return result;
	}
}
