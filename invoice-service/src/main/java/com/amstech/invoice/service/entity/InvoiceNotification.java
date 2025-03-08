package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the invoice_notification database table.
 * 
 */
@Entity
@Table(name="invoice_notification")
@NamedQuery(name="InvoiceNotification.findAll", query="SELECT i FROM InvoiceNotification i")
public class InvoiceNotification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="_id")
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Lob
	private String message;

	private String type;

	@Column(name="user_id")
	private int userId;

	public InvoiceNotification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

}