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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@JoinColumn(name="invoice_id1")
	private ServiceInvoice serviceInvoice1;

	//bi-directional many-to-one association to ServiceInvoice
	@ManyToOne
	@JoinColumn(name="invoice_id2")
	private ServiceInvoice serviceInvoice2;

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

	public ServiceInvoice getServiceInvoice1() {
		return this.serviceInvoice1;
	}

	public void setServiceInvoice1(ServiceInvoice serviceInvoice1) {
		this.serviceInvoice1 = serviceInvoice1;
	}

	public ServiceInvoice getServiceInvoice2() {
		return this.serviceInvoice2;
	}

	public void setServiceInvoice2(ServiceInvoice serviceInvoice2) {
		this.serviceInvoice2 = serviceInvoice2;
	}

}