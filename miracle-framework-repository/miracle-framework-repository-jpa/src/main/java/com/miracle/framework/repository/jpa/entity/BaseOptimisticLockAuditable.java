package com.miracle.framework.repository.jpa.entity;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

@MappedSuperclass
public abstract class BaseOptimisticLockAuditable<U> extends BasePersistable implements OptimisticLockable, Auditable<U, String> {
	
	private static final long serialVersionUID = -7222110948798154555L;
	
	@Version
	private long version;
	
	@ManyToOne
	private U createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@ManyToOne
	private U lastModifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@Override
	public final long getVersion() {
		return version;
	}
	
	@Override
	public final void setVersion(final long version) {
		this.version = version;
	}
	
	public final U getCreatedBy() {
		return createdBy;
	}
	
	public final void setCreatedBy(final U createdBy) {
		this.createdBy = createdBy;
	}
	
	public final DateTime getCreatedDate() {
		return null == createdDate ? null : new DateTime(createdDate);
	}
	
	public final void setCreatedDate(final DateTime createdDate) {
		this.createdDate = null == createdDate ? null : createdDate.toDate();
	}
	
	public final U getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public final void setLastModifiedBy(final U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public final DateTime getLastModifiedDate() {
		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}
	
	public final void setLastModifiedDate(final DateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
	}
}
