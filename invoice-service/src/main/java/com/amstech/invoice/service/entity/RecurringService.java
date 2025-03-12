package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the recurring_services database table.
 * 
 */
@Entity
@Table(name="recurring_services")
@NamedQuery(name="RecurringService.findAll", query="SELECT r FROM RecurringService r")
public class RecurringService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="billing_cycle")
	private String billingCycle;

	@Column(name="created_at")
	private Timestamp createdAt;

	private int quantity;

	@Column(name="service_name")
	private String serviceName;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	@Column(name="unit_price")
	private BigDecimal unitPrice;

	@JoinColumn(name="invoice_id")
	private RecurringInvoice recurringInvoice;

	public RecurringService() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBillingCycle() {
		return this.billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

 RecurringInvoice getRecurringInvoice() {
		return this.recurringInvoice;
	}

	public void setRecurringInvoice(RecurringInvoice recurringInvoice) {
		this.recurringInvoice = recurringInvoice;

	}

}