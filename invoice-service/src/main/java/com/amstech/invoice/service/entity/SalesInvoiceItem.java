package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the sales_invoice_items database table.
 * 
 */
@Entity
@Table(name="sales_invoice_items")
@NamedQuery(name="SalesInvoiceItem.findAll", query="SELECT s FROM SalesInvoiceItem s")
public class SalesInvoiceItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private int quantity;

	private BigDecimal subtotal;

	@Column(name="unit_price")
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to SalesInvoice
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private SalesInvoice salesInvoice;

	public SalesInvoiceItem() {
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

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public SalesInvoice getSalesInvoice() {
		return this.salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

}