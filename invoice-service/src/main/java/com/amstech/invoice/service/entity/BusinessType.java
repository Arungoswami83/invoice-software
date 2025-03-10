package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the business_type database table.
 * 
 */
@Entity
@Table(name="business_type")
@NamedQuery(name="BusinessType.findAll", query="SELECT b FROM BusinessType b")
public class BusinessType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	public BusinessType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}