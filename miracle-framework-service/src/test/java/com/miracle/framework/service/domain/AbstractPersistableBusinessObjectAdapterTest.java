package com.miracle.framework.service.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.miracle.framework.service.domain.fixture.BarBusinessObject;
import com.miracle.framework.service.domain.fixture.FooPersistableEntity;

public final class AbstractPersistableBusinessObjectAdapterTest {
	
	@Test
	public void convertTo() {
		BarBusinessObject barBusinessObject = new BarBusinessObject("foo", "bar");
		FooPersistableEntity fooPersistableEntity = barBusinessObject.convertTo();
		assertThat(fooPersistableEntity.getShare(), is("foo"));
		assertThat(fooPersistableEntity.getPersistableEntityOnly(), nullValue());
	}
	
	@Test
	public void fillFrom() {
		FooPersistableEntity fooPersistableEntity = new FooPersistableEntity("foo", "bar");
		BarBusinessObject barBusinessObject = new BarBusinessObject();
		barBusinessObject.fillFrom(fooPersistableEntity);
		assertThat(barBusinessObject.getShare(), is("foo"));
		assertThat(barBusinessObject.getBusinessObjectOnly(), nullValue());
	}
}
