package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;


/**
 * The persistent class for the payments database table.
 * 
 */
@Entity
@Table(name="payments")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @ManyToOne(fetch = FetchType.LAZY)  
	    @JoinColumn(name = "invoice_id", nullable = false)
	    private Invoice invoice;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "payment_method")
	    private PaymentMethod paymentMethod;

	    @Column(nullable = false, precision = 12, scale = 2)
	    private BigDecimal amount;

	    @Column(name = "payment_date", nullable = false)
	    private LocalDate paymentDate;

	    @Lob
	    private String notes;

	    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	    private java.sql.Timestamp createdAt;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Invoice getInvoice() {
			return invoice;
		}

		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}

		public PaymentMethod getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(PaymentMethod paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public LocalDate getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public java.sql.Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(java.sql.Timestamp createdAt) {
			this.createdAt = createdAt;
		}
	    
	}

