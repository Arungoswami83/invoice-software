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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	private int quantity;

	private BigDecimal subtotal;

	@Column(name="unit_price")
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to SalesInvoice
	@ManyToOne
	@JoinColumn(name="invoice_id1")
	private SalesInvoices salesInvoice1;

	//bi-directional many-to-one association to SalesInvoice
	@ManyToOne
	@JoinColumn(name="invoice_id2")
	private SalesInvoices salesInvoice2;

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

	public SalesInvoices getSalesInvoice1() {
		return this.salesInvoice1;
	}

	public void setSalesInvoice1(SalesInvoices salesInvoice1) {
		this.salesInvoice1 = salesInvoice1;
	}

	public SalesInvoices getSalesInvoice2() {
		return this.salesInvoice2;
	}

	public void setSalesInvoice2(SalesInvoices salesInvoice2) {
		this.salesInvoice2 = salesInvoice2;
	}

}