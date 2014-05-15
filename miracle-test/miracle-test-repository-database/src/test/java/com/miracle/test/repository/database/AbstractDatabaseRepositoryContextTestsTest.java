package com.miracle.test.repository.database;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.miracle.test.repository.database.fixture.entity.Foo;
import com.miracle.test.repository.database.fixture.repository.FooRepository;

public class AbstractDatabaseRepositoryContextTestsTest extends AbstractDatabaseRepositoryContextTests {
	
	@Resource
	private FooRepository fooRepository;
	
	@Test
	@DatabaseSetup("Empty.xml")
	@ExpectedDatabase(value = "AfterSave.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public final void save() {
		long firstId = fooRepository.save(new Foo(1, 1, "BBB")).getId();
		fooRepository.save(Arrays.asList(new Foo(2, "BBB"), new Foo(3, "CCC")));
		fooRepository.save(new Foo(firstId, 1, "AAA"));
	}
	
	@Test
	@DatabaseSetup("NormalData.xml")
	@ExpectedDatabase(value = "AfterDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public final void delete() {
		fooRepository.delete(1L);
		fooRepository.delete(new Foo(2L, 0, null));
	}
	
	@Test
	@DatabaseSetup("NormalData.xml")
	public final void findOne() {
		assertThat(fooRepository.findOne(1L), is(new Foo(1, 1, "AAA")));
		assertThat(fooRepository.findOne(2L), is(new Foo(2, 2, "BBB")));
		assertThat(fooRepository.findOne(3L), is(new Foo(3, 3, "CCC")));
	}
	
	@Test
	@DatabaseSetup("NormalData.xml")
	public final void findByNumberAndValue() {
		assertThat(fooRepository.findByNumberAndValue(1, "AAA").get(0), is(new Foo(1, 1, "AAA")));
		assertThat(fooRepository.findByNumberAndValue(2, "B").get(0), is(new Foo(2, 2, "BBB")));
		assertThat(fooRepository.findByNumberAndValue(2, "AAA").size(), is(0));
	}
}
