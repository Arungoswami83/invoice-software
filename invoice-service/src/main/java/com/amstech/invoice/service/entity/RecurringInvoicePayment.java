package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the recurring_invoice_payments database table.
 * 
 */
@Entity
@Table(name="recurring_invoice_payments")
@NamedQuery(name="RecurringInvoicePayment.findAll", query="SELECT r FROM RecurringInvoicePayment r")
public class RecurringInvoicePayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_id")
	private int paymentId;
	



	@Temporal(TemporalType.DATE)
	@Column(name="last_payment_date")
	private Date lastPaymentDate;

	@Temporal(TemporalType.DATE)
	@Column(name="next_payment_date")
	private Date nextPaymentDate;

	@Column(name="payment_status")
	private String paymentStatus;
	
	@ManyToOne
	@JoinColumn(name="id")
	private RecurringInvoice recurringInvoice;

	public RecurringInvoicePayment() {
	}

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	public Date getLastPaymentDate() {
		return this.lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public Date getNextPaymentDate() {
		return this.nextPaymentDate;
	}

	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public RecurringInvoice getRecurringInvoice() {
		return this.recurringInvoice;
	}

	public void setRecurringInvoice(RecurringInvoice recurringInvoice) {
		this.recurringInvoice = recurringInvoice;
	}

}