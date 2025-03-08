package com.amstech.invoice.service.entity;

import java.io.Serializable;
import java.util.Optional;

import jakarta.persistence.*;


/**
 * The persistent class for the business_type database table.
 * 
 */
@Entity
@Table(name="business_types")
@NamedQuery(name="BusinessTypes.findAll", query="SELECT b FROM BusinessTypes b")
public class BusinessTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public BusinessTypes() {
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

	public Optional<Currency> findById(int businessTypesId) {
		return this.findById(businessTypesId);
	}

	

}