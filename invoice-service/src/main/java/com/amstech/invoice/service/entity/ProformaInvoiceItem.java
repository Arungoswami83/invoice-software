package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the proforma_invoice_items database table.
 * 
 */
@Entity
@Table(name="proforma_invoice_items")
@NamedQuery(name="ProformaInvoiceItem.findAll", query="SELECT p FROM ProformaInvoiceItem p")
public class ProformaInvoiceItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal cost;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String description;

	private BigDecimal tax;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	//bi-directional many-to-one association to ProformaInvoice
	@ManyToOne
	@JoinColumn(name="proforma_id1")
	private ProformaInvoice proformaInvoice1;

	//bi-directional many-to-one association to ProformaInvoice
	@ManyToOne
	@JoinColumn(name="proforma_id2")
	private ProformaInvoice proformaInvoice2;

	public ProformaInvoiceItem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
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

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ProformaInvoice getProformaInvoice1() {
		return this.proformaInvoice1;
	}

	public void setProformaInvoice1(ProformaInvoice proformaInvoice1) {
		this.proformaInvoice1 = proformaInvoice1;
	}

	public ProformaInvoice getProformaInvoice2() {
		return this.proformaInvoice2;
	}

	public void setProformaInvoice2(ProformaInvoice proformaInvoice2) {
		this.proformaInvoice2 = proformaInvoice2;
	}

}