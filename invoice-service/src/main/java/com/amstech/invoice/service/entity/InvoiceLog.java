package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the invoice_logs database table.
 * 
 */
@Entity
@Table(name="invoice_logs")
@NamedQuery(name="InvoiceLog.findAll", query="SELECT i FROM InvoiceLog i")
public class InvoiceLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String action;

	@Column(name="action_date")
	private Timestamp actionDate;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	private Invoice invoice;

	public InvoiceLog() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Timestamp actionDate) {
		this.actionDate = actionDate;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}