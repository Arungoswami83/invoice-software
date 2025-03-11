package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the sales_invoices database table.
 * 
 */
@Entity
@Table(name="sales_invoices")
@NamedQuery(name="SalesInvoices.findAll", query="SELECT s FROM SalesInvoices s")
public class SalesInvoices implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "is_deleted")
	private int isDeleted;
	@ManyToOne
    @JoinColumn(name = "client_id", nullable = false) // Ensures NOT NULL
    private Client client;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Temporal(TemporalType.DATE)
	private Date date;

	private BigDecimal discount;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Column(name="payment_term")
	private String paymentTerm;

	private double price;

	@Lob
	private String signature;

	private String status;

	private BigDecimal subtotal;

	private BigDecimal tax;

	private BigDecimal total;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to Analytic
	@OneToMany(mappedBy = "salesInvoices", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Analytic> analytics;

	//bi-directional many-to-one association to Dashboard
	@OneToMany(mappedBy="salesInvoices")
	private List<Dashboard> dashboards;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="salesInvoices")
	private List<Report> reports;

	//bi-directional many-to-one association to SalesInvoiceItem
	@OneToMany(mappedBy="salesInvoice1")
	private List<SalesInvoiceItem> salesInvoiceItems1;

	//bi-directional many-to-one association to SalesInvoiceItem
	@OneToMany(mappedBy="salesInvoice2")
	private List<SalesInvoiceItem> salesInvoiceItems2;

	public SalesInvoices() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Analytic> getAnalytics() {
		return this.analytics;
	}

	public void setAnalytics(List<Analytic> analytics) {
		this.analytics = analytics;
	}

	public Analytic addAnalytic(Analytic analytic) {
		getAnalytics().add(analytic);
		analytic.setSalesInvoices(this);

		return analytic;
	}

	public Analytic removeAnalytic(Analytic analytic) {
		getAnalytics().remove(analytic);
		analytic.setSalesInvoices(null);

		return analytic;
	}

	public List<Dashboard> getDashboards() {
		return this.dashboards;
	}

	public void setDashboards(List<Dashboard> dashboards) {
		this.dashboards = dashboards;
	}

	public Dashboard addDashboard(Dashboard dashboard) {
		getDashboards().add(dashboard);
		dashboard.setSalesInvoices(this);

		return dashboard;
	}

	public Dashboard removeDashboard(Dashboard dashboard) {
		getDashboards().remove(dashboard);
		dashboard.setSalesInvoices(null);

		return dashboard;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setSalesInvoice(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setSalesInvoice(null);

		return report;
	}

	public List<SalesInvoiceItem> getSalesInvoiceItems1() {
		return this.salesInvoiceItems1;
	}

	public void setSalesInvoiceItems1(List<SalesInvoiceItem> salesInvoiceItems1) {
		this.salesInvoiceItems1 = salesInvoiceItems1;
	}

	public SalesInvoiceItem addSalesInvoiceItems1(SalesInvoiceItem salesInvoiceItems1) {
		getSalesInvoiceItems1().add(salesInvoiceItems1);
		salesInvoiceItems1.setSalesInvoice1(this);

		return salesInvoiceItems1;
	}

	public SalesInvoiceItem removeSalesInvoiceItems1(SalesInvoiceItem salesInvoiceItems1) {
		getSalesInvoiceItems1().remove(salesInvoiceItems1);
		salesInvoiceItems1.setSalesInvoice1(null);

		return salesInvoiceItems1;
	}

	public List<SalesInvoiceItem> getSalesInvoiceItems2() {
		return this.salesInvoiceItems2;
	}

	public void setSalesInvoiceItems2(List<SalesInvoiceItem> salesInvoiceItems2) {
		this.salesInvoiceItems2 = salesInvoiceItems2;
	}

	public SalesInvoiceItem addSalesInvoiceItems2(SalesInvoiceItem salesInvoiceItems2) {
		getSalesInvoiceItems2().add(salesInvoiceItems2);
		salesInvoiceItems2.setSalesInvoice2(this);

		return salesInvoiceItems2;
	}

	public SalesInvoiceItem removeSalesInvoiceItems2(SalesInvoiceItem salesInvoiceItems2) {
		getSalesInvoiceItems2().remove(salesInvoiceItems2);
		salesInvoiceItems2.setSalesInvoice2(null);

		return salesInvoiceItems2;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	

}