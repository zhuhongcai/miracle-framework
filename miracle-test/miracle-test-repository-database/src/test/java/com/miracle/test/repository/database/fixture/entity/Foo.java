package com.miracle.test.repository.database.fixture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "foo")
public final class Foo implements Serializable {
	
	private static final long serialVersionUID = -8791441175976343611L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private int number;
	
	private String value;
	
	public Foo() { }
	
	public Foo(final int number, final String value) {
		this.number = number;
		this.value = value;
	}
	
	public Foo(final long id, final int number, final String value) {
		this(number, value);
		this.id = id;
	}
}
