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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String action;

	@Column(name="action_date")
	private Timestamp actionDate;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id1")
	private Invoice invoice1;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id2")
	private Invoice invoice2;

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

	public Invoice getInvoice1() {
		return this.invoice1;
	}

	public void setInvoice1(Invoice invoice1) {
		this.invoice1 = invoice1;
	}

	public Invoice getInvoice2() {
		return this.invoice2;
	}

	public void setInvoice2(Invoice invoice2) {
		this.invoice2 = invoice2;
	}

}