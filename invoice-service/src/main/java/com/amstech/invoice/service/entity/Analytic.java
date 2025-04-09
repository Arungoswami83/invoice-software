package com.amstech.invoice.service.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the analytics database table.
 * 
 */
@Entity
@Table(name="analytics")
@NamedQuery(name="Analytic.findAll", query="SELECT a FROM Analytic a")
public class Analytic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	private Invoice invoice;
	
	 @Column(name = "total_amount")
	    private BigDecimal totalAmount;

	    @Column(name = "tax_amount")
	    private BigDecimal taxAmount;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "payment_status", nullable = false)
	    private PaymentStatus paymentStatus;
	    
	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

	    
	    public Analytic() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
//	public Dashboard addDashboard(Dashboard dashboard) {
//		getDashboards().add(dashboard);
//		dashboard.setAnalytic(this);
//
//		return dashboard;
//	}
//
//	public Dashboard removeDashboard(Dashboard dashboard) {
//		getDashboards().remove(dashboard);
//		dashboard.setAnalytic(null);
//
//		return dashboard;
//	}

}