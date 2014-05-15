package com.miracle.framework.service.domain.fixture;

import lombok.Getter;
import lombok.Setter;

import com.miracle.framework.service.domain.AbstractPersistableBusinessObjectAdapter;

@Getter
@Setter
public final class BarBusinessObject extends AbstractPersistableBusinessObjectAdapter<FooPersistableEntity> {
	
	private String share;
	private String businessObjectOnly;
	
	public BarBusinessObject() {
		super(FooPersistableEntity.class);
	}
	
	public BarBusinessObject(final String share, final String businessObjectOnly) {
		this();
		this.share = share;
		this.businessObjectOnly = businessObjectOnly;
	}
}
