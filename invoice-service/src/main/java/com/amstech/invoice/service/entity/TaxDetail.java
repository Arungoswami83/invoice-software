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
	private int id;

	private BigDecimal amount;

	private BigDecimal percentage;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	private Invoice invoice;

	//bi-directional many-to-one association to InvoiceItem
	@ManyToOne
	@JoinColumn(name="item_id")
	private InvoiceItem invoiceItem;

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

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public InvoiceItem getInvoiceItem() {
		return this.invoiceItem;
	}

	public void setInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

}