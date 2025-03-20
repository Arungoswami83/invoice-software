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

	//bi-directional many-to-one association to SalesInvoice
	@ManyToOne
	@JoinColumn(name="sales_invoices")
	private SalesInvoice salesInvoice;
	
	@ManyToOne
	@JoinColumn(name = "sales_invoice_id")
	private SalesInvoices salesInvoices;
	
	 @Column(name = "total_amount", nullable = false, precision = 38, scale = 2)
	    private BigDecimal totalAmount;

	    @Column(name = "tax_amount", nullable = false, precision = 38, scale = 2)
	    private BigDecimal taxAmount;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "payment_status", nullable = false)
	    private PaymentStatus paymentStatus;
	    
	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

//	//bi-directional many-to-one association to Dashboard
//	@OneToMany(mappedBy="analytic")
//	private List<Dashboard> dashboards;

	public Analytic() {
	}
	 public void Analytics(Invoice invoice, SalesInvoice salesInvoice, BigDecimal totalAmount, BigDecimal taxAmount, PaymentStatus paymentStatus) {
	        this.invoice = invoice;
	        this.salesInvoice = salesInvoice;
	        this.totalAmount = totalAmount;
	        this.taxAmount = taxAmount;
	        this.paymentStatus = paymentStatus;
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

	public SalesInvoice getSalesInvoice() {
		return this.salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

	public SalesInvoices getSalesInvoices() {
		return salesInvoices;
	}

	public void setSalesInvoices(SalesInvoices salesInvoices) {
		this.salesInvoices = salesInvoices;
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