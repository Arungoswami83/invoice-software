package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tax_details database table.
 * 
 */
@Entity
@Table(name="tax_details")
@NamedQuery(name="TaxDetail.findAll", query="SELECT t FROM TaxDetail t")
public class TaxDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal amount;

	private BigDecimal percentage;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id1")
	private Invoice invoice1;

	//bi-directional many-to-one association to InvoiceItem
	@ManyToOne
	@JoinColumn(name="item_id1")
	private InvoiceItem invoiceItem1;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id2")
	private Invoice invoice2;

	//bi-directional many-to-one association to InvoiceItem
	@ManyToOne
	@JoinColumn(name="item_id2")
	private InvoiceItem invoiceItem2;

	public TaxDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public Invoice getInvoice1() {
		return this.invoice1;
	}

	public void setInvoice1(Invoice invoice1) {
		this.invoice1 = invoice1;
	}

	public InvoiceItem getInvoiceItem1() {
		return this.invoiceItem1;
	}

	public void setInvoiceItem1(InvoiceItem invoiceItem1) {
		this.invoiceItem1 = invoiceItem1;
	}

	public Invoice getInvoice2() {
		return this.invoice2;
	}

	public void setInvoice2(Invoice invoice2) {
		this.invoice2 = invoice2;
	}

	public InvoiceItem getInvoiceItem2() {
		return this.invoiceItem2;
	}

	public void setInvoiceItem2(InvoiceItem invoiceItem2) {
		this.invoiceItem2 = invoiceItem2;
	}

}