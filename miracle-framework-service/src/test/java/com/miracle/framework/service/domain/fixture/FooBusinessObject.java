package com.miracle.framework.service.domain.fixture;

import lombok.Getter;
import lombok.Setter;

import com.miracle.framework.service.domain.AbstractPersistableBusinessObject;

@Getter
@Setter
public final class FooBusinessObject extends AbstractPersistableBusinessObject<FooPersistableEntity> {
	
	private String share;
	private String businessObjectOnly;
	
	public FooBusinessObject() {
		super(FooPersistableEntity.class);
	}
	
	public FooBusinessObject(final String share, final String businessObjectOnly) {
		this();
		this.share = share;
		this.businessObjectOnly = businessObjectOnly;
	}
	
	@Override
	protected void customizeConvert(final FooPersistableEntity persistableEntity) {
		persistableEntity.setPersistableEntityOnly(businessObjectOnly);
	}
	
	@Override
	protected void customizeFill(final FooPersistableEntity persistableEntity) {
		businessObjectOnly = persistableEntity.getPersistableEntityOnly();
	}
}
