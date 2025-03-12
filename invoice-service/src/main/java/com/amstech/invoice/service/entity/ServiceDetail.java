package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the service_details database table.
 * 
 */
@Entity
@Table(name="service_details")
@NamedQuery(name="ServiceDetail.findAll", query="SELECT s FROM ServiceDetail s")
public class ServiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String description;

	@Column(name="hourly_rate")
	private BigDecimal hourlyRate;

	@Column(name="hours_worked")
	private int hoursWorked;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	//bi-directional many-to-one association to ServiceInvoice
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private ServiceInvoice serviceInvoice;

	public ServiceDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getHourlyRate() {
		return this.hourlyRate;
	}

	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public int getHoursWorked() {
		return this.hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ServiceInvoice getServiceInvoice() {
		return this.serviceInvoice;
	}

	public void setServiceInvoice(ServiceInvoice serviceInvoice) {
		this.serviceInvoice = serviceInvoice;
	}

}