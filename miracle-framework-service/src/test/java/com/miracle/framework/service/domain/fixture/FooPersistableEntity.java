package com.miracle.framework.service.domain.fixture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.miracle.framework.repository.entity.PersistableEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class FooPersistableEntity implements PersistableEntity {
	
	private String share;
	private String persistableEntityOnly;
}
