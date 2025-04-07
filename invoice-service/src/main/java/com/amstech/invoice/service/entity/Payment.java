package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


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
	    private int id;

	 	 @ManyToOne
	     @JoinColumn(name = "invoice_id", nullable = false)
	     private Invoice invoice; 
	 	 
	 	 @Column(name = "payment_status", nullable = false)
	     @Enumerated(EnumType.STRING)
	     private PaymentStatus paymentStatus;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "payment_method")
	    private PaymentMethod paymentMethod;
	    
	    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Transaction> transactions;

	    @Column(name = "notes", columnDefinition = "TEXT")
	    private String notes;
	    
	    @Column(name = "amount_paid")
	    private BigDecimal amountPaid;

	    @Column(name = "payment_date")
	    private LocalDate paymentDate;
	    
	    @Column(name = "created_at")
	    private LocalDateTime createdAt = LocalDateTime.now();

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt = LocalDateTime.now();
	    
	    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
	    private List<Report> reports;

	    @PreUpdate
	    public void setLastUpdate() {
	    this.updatedAt = LocalDateTime.now();
	    }
	    
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public List<Report> getReports() {
			return reports;
		}

		public void setReports(List<Report> reports) {
			this.reports = reports;
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

		public PaymentStatus getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(PaymentStatus paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public PaymentMethod getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(PaymentMethod paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public List<Transaction> getTransactions() {
			return transactions;
		}

		public void setTransactions(List<Transaction> transactions) {
			this.transactions = transactions;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public BigDecimal getAmountPaid() {
			return amountPaid;
		}

		public void setAmountPaid(BigDecimal amountPaid) {
			this.amountPaid = amountPaid;
		}

		public LocalDate getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
}
	