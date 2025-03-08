package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the recurring_invoice_items database table.
 * 
 */
@Entity
@Table(name="recurring_invoice_items")
@NamedQuery(name="RecurringInvoiceItem.findAll", query="SELECT r FROM RecurringInvoiceItem r")
public class RecurringInvoiceItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="billing_cycle")
	private String billingCycle;

	private int quantity;

	@Column(name="service_name")
	private String serviceName;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	@Column(name="unit_price")
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to RecurringInvoice
	@ManyToOne
	@JoinColumn(name="invoice_id1")
	private RecurringInvoice recurringInvoice1;

	//bi-directional many-to-one association to RecurringInvoice
	@ManyToOne
	@JoinColumn(name="invoice_id2")
	private RecurringInvoice recurringInvoice2;

	public RecurringInvoiceItem() {
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

	public RecurringInvoice getRecurringInvoice1() {
		return this.recurringInvoice1;
	}

	public void setRecurringInvoice1(RecurringInvoice recurringInvoice1) {
		this.recurringInvoice1 = recurringInvoice1;
	}

	public RecurringInvoice getRecurringInvoice2() {
		return this.recurringInvoice2;
	}

	public void setRecurringInvoice2(RecurringInvoice recurringInvoice2) {
		this.recurringInvoice2 = recurringInvoice2;
	}

}