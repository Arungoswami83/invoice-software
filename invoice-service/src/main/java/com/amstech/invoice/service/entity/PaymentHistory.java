package com.amstech.invoice.service.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="payment_history")
@NamedQuery(name="PaymentHistory.findAll", query="SELECT b FROM PaymentHistory b")
public class PaymentHistory implements Serializable{
	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
	
		@ManyToOne
	    @JoinColumn(name = "invoice_id", nullable = false)
	    private Invoice invoice;

	    @Column(name = "payment_method", length = 100)
	    private String paymentMethod;

	    @Column(name = "amount", nullable = false, precision = 38, scale = 2)
	    private Double amount;

	    @Column(name = "transaction_date", nullable = false, updatable = false)
	    private Timestamp transactionDate;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "status")
	    private PaymentStatus status;

	    @Column(name = "changed_by", nullable = false, length = 100)
	    private String changedBy;

	    @Column(name = "changed_at", nullable = false, updatable = false)
	    private Timestamp changedAt;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Invoice getInvoice() {
			return invoice;
		}

		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}

		public String getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public Timestamp getTransactionDate() {
			return transactionDate;
		}

		public void setTransactionDate(Timestamp transactionDate) {
			this.transactionDate = transactionDate;
		}

		public PaymentStatus getStatus() {
			return status;
		}

		public void setStatus(PaymentStatus status) {
			this.status = status;
		}

		public String getChangedBy() {
			return changedBy;
		}

		public void setChangedBy(String changedBy) {
			this.changedBy = changedBy;
		}

		public Timestamp getChangedAt() {
			return changedAt;
		}

		public void setChangedAt(Timestamp changedAt) {
			this.changedAt = changedAt;
		}
	
}






