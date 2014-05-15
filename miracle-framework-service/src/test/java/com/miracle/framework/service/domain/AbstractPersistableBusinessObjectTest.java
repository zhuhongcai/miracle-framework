package com.miracle.framework.service.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.miracle.framework.service.domain.fixture.FooBusinessObject;
import com.miracle.framework.service.domain.fixture.FooPersistableEntity;

public final class AbstractPersistableBusinessObjectTest {
	
	@Test
	public void convertTo() {
		FooBusinessObject fooBusinessObject = new FooBusinessObject("foo", "bar");
		FooPersistableEntity fooPersistableEntity = fooBusinessObject.convertTo();
		assertThat(fooPersistableEntity.getShare(), is("foo"));
		assertThat(fooPersistableEntity.getPersistableEntityOnly(), is("bar"));
	}
	
	@Test
	public void fillFrom() {
		FooPersistableEntity fooPersistableEntity = new FooPersistableEntity("foo", "bar");
		FooBusinessObject fooBusinessObject = new FooBusinessObject();
		fooBusinessObject.fillFrom(fooPersistableEntity);
		assertThat(fooBusinessObject.getShare(), is("foo"));
		assertThat(fooBusinessObject.getBusinessObjectOnly(), is("bar"));
	}
}
