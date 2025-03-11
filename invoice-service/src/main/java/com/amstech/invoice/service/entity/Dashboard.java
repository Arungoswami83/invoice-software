package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the dashboard database table.
 * 
 */
@Entity
@NamedQuery(name="Dashboard.findAll", query="SELECT d FROM Dashboard d")
public class Dashboard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	private BigDecimal inflow;

	private BigDecimal outflow;

	@Column(name="total_expense")
	private BigDecimal totalExpense;

	@Column(name="total_income")
	private BigDecimal totalIncome;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to Analytic
	@ManyToOne
	@JoinColumn(name="analytics_id")
	private Analytic analytic;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	private Invoice invoice;

	//bi-directional many-to-one association to Report
	@ManyToOne
	@JoinColumn(name="reports_id")
	private Report report;

	//bi-directional many-to-one association to SalesInvoice
	@ManyToOne
	@JoinColumn(name="sales_invoices")
	private SalesInvoices salesInvoices;

	

	public Dashboard() {
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

	public BigDecimal getInflow() {
		return this.inflow;
	}

	public void setInflow(BigDecimal inflow) {
		this.inflow = inflow;
	}

	public BigDecimal getOutflow() {
		return this.outflow;
	}

	public void setOutflow(BigDecimal outflow) {
		this.outflow = outflow;
	}

	public BigDecimal getTotalExpense() {
		return this.totalExpense;
	}

	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}

	public BigDecimal getTotalIncome() {
		return this.totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Analytic getAnalytic() {
		return this.analytic;
	}

	public void setAnalytic(Analytic analytic) {
		this.analytic = analytic;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Report getReport() {
		return this.report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public SalesInvoices getSalesInvoices() {
		return this.salesInvoices;
	}

	public void setSalesInvoices(SalesInvoices salesInvoice) {
		this.salesInvoices = salesInvoice;
	}

	

}