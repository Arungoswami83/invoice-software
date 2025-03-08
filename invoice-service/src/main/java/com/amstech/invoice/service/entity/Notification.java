package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the notifications database table.
 * 
 */
@Entity
@Table(name="notifications")
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp date;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Lob
	private String message;

	private String type;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id1")
	private Invoice invoice1;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id2")
	private Invoice invoice2;

	public Notification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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