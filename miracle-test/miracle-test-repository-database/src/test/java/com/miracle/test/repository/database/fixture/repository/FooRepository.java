package com.miracle.test.repository.database.fixture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.miracle.test.repository.database.fixture.entity.Foo;

public interface FooRepository extends CrudRepository<Foo, Long> {
	
	@Query("FROM Foo f WHERE f.number=?1 AND value like %?2%")
	List<Foo> findByNumberAndValue(int number, String value);
}
