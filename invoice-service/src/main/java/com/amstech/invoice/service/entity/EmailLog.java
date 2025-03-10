package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the email_logs database table.
 * 
 */
@Entity
@Table(name="email_logs")
@NamedQuery(name="EmailLog.findAll", query="SELECT e FROM EmailLog e")
public class EmailLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String body;

	@Column(name="customer_email")
	private String customerEmail;

	@Column(name="sent_at")
	private Timestamp sentAt;

	private String status;

	private String subject;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	private Invoice invoice;

	public EmailLog() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Timestamp getSentAt() {
		return this.sentAt;
	}

	public void setSentAt(Timestamp sentAt) {
		this.sentAt = sentAt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}