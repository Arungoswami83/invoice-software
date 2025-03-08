package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int isdeleted;

	private BigDecimal balance;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="customer_email")
	private String customerEmail;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="customer_phone")
	private String customerPhone;

	private BigDecimal discount;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="grand_total")
	private BigDecimal grandTotal;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="issue_date")
	private Date issueDate;

	private BigDecimal paid;

	@Column(name="product_code")
	private String productCode;

	@Lob
	private String quantity;

	private BigDecimal shipping;

	private String status;

	@Column(name="sub_total")
	private BigDecimal subTotal;

	private BigDecimal tax;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to Analytic
	@OneToMany(mappedBy="invoice")
	private List<Analytic> analytics;

	//bi-directional many-to-one association to Dashboard
	@OneToMany(mappedBy="invoice")
	private List<Dashboard> dashboards;

	//bi-directional many-to-one association to EmailLog
	@OneToMany(mappedBy="invoice")
	private List<EmailLog> emailLogs;

	//bi-directional many-to-one association to GenerateInvoice
	@OneToMany(mappedBy="invoice")
	private List<GenerateInvoice> generateInvoices;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	//bi-directional many-to-one association to InvoiceItem
	@ManyToOne
	@JoinColumn(name="invoice_items")
	private InvoiceItem invoiceItem;

	//bi-directional many-to-one association to InvoiceType
	@ManyToOne
	@JoinColumn(name="invoice_types_id")
	private InvoiceType invoiceType;

	//bi-directional many-to-one association to Payment
	@ManyToOne
	@JoinColumn(name="payments_id")
	private Payment payment;

	//bi-directional many-to-one association to Report
	@ManyToOne
	@JoinColumn(name="reports_id")
	private Report report;

	//bi-directional many-to-one association to InvoiceLog
	@OneToMany(mappedBy="invoice1")
	private List<InvoiceLog> invoiceLogs1;

	//bi-directional many-to-one association to InvoiceLog
	@OneToMany(mappedBy="invoice2")
	private List<InvoiceLog> invoiceLogs2;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="invoice1")
	private List<Notification> notifications1;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="invoice2")
	private List<Notification> notifications2;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="invoice1")
	private List<Payment> payments1;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="invoice2")
	private List<Payment> payments2;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="invoice")
	private List<Report> reports;

	//bi-directional many-to-one association to TaxDetail
	@OneToMany(mappedBy="invoice1")
	private List<TaxDetail> taxDetails1;

	//bi-directional many-to-one association to TaxDetail
	@OneToMany(mappedBy="invoice2")
	private List<TaxDetail> taxDetails2;

	public Invoice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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

	public BigDecimal getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getPaid() {
		return this.paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getShipping() {
		return this.shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
		analytic.setInvoice(this);

		return analytic;
	}

	public Analytic removeAnalytic(Analytic analytic) {
		getAnalytics().remove(analytic);
		analytic.setInvoice(null);

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
		dashboard.setInvoice(this);

		return dashboard;
	}

	public Dashboard removeDashboard(Dashboard dashboard) {
		getDashboards().remove(dashboard);
		dashboard.setInvoice(null);

		return dashboard;
	}

	public List<EmailLog> getEmailLogs() {
		return this.emailLogs;
	}

	public void setEmailLogs(List<EmailLog> emailLogs) {
		this.emailLogs = emailLogs;
	}

	public EmailLog addEmailLog(EmailLog emailLog) {
		getEmailLogs().add(emailLog);
		emailLog.setInvoice(this);

		return emailLog;
	}

	public EmailLog removeEmailLog(EmailLog emailLog) {
		getEmailLogs().remove(emailLog);
		emailLog.setInvoice(null);

		return emailLog;
	}

	public List<GenerateInvoice> getGenerateInvoices() {
		return this.generateInvoices;
	}

	public void setGenerateInvoices(List<GenerateInvoice> generateInvoices) {
		this.generateInvoices = generateInvoices;
	}

	public GenerateInvoice addGenerateInvoice(GenerateInvoice generateInvoice) {
		getGenerateInvoices().add(generateInvoice);
		generateInvoice.setInvoice(this);

		return generateInvoice;
	}

	public GenerateInvoice removeGenerateInvoice(GenerateInvoice generateInvoice) {
		getGenerateInvoices().remove(generateInvoice);
		generateInvoice.setInvoice(null);

		return generateInvoice;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public InvoiceItem getInvoiceItem() {
		return this.invoiceItem;
	}

	public void setInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public InvoiceType getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Report getReport() {
		return this.report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public List<InvoiceLog> getInvoiceLogs1() {
		return this.invoiceLogs1;
	}

	public void setInvoiceLogs1(List<InvoiceLog> invoiceLogs1) {
		this.invoiceLogs1 = invoiceLogs1;
	}

	public InvoiceLog addInvoiceLogs1(InvoiceLog invoiceLogs1) {
		getInvoiceLogs1().add(invoiceLogs1);
		invoiceLogs1.setInvoice1(this);

		return invoiceLogs1;
	}

	public InvoiceLog removeInvoiceLogs1(InvoiceLog invoiceLogs1) {
		getInvoiceLogs1().remove(invoiceLogs1);
		invoiceLogs1.setInvoice1(null);

		return invoiceLogs1;
	}

	public List<InvoiceLog> getInvoiceLogs2() {
		return this.invoiceLogs2;
	}

	public void setInvoiceLogs2(List<InvoiceLog> invoiceLogs2) {
		this.invoiceLogs2 = invoiceLogs2;
	}

	public InvoiceLog addInvoiceLogs2(InvoiceLog invoiceLogs2) {
		getInvoiceLogs2().add(invoiceLogs2);
		invoiceLogs2.setInvoice2(this);

		return invoiceLogs2;
	}

	public InvoiceLog removeInvoiceLogs2(InvoiceLog invoiceLogs2) {
		getInvoiceLogs2().remove(invoiceLogs2);
		invoiceLogs2.setInvoice2(null);

		return invoiceLogs2;
	}

	public List<Notification> getNotifications1() {
		return this.notifications1;
	}

	public void setNotifications1(List<Notification> notifications1) {
		this.notifications1 = notifications1;
	}

	public Notification addNotifications1(Notification notifications1) {
		getNotifications1().add(notifications1);
		notifications1.setInvoice1(this);

		return notifications1;
	}

	public Notification removeNotifications1(Notification notifications1) {
		getNotifications1().remove(notifications1);
		notifications1.setInvoice1(null);

		return notifications1;
	}

	public List<Notification> getNotifications2() {
		return this.notifications2;
	}

	public void setNotifications2(List<Notification> notifications2) {
		this.notifications2 = notifications2;
	}

	public Notification addNotifications2(Notification notifications2) {
		getNotifications2().add(notifications2);
		notifications2.setInvoice2(this);

		return notifications2;
	}

	public Notification removeNotifications2(Notification notifications2) {
		getNotifications2().remove(notifications2);
		notifications2.setInvoice2(null);

		return notifications2;
	}

	public List<Payment> getPayments1() {
		return this.payments1;
	}

	public void setPayments1(List<Payment> payments1) {
		this.payments1 = payments1;
	}

	public Payment addPayments1(Payment payments1) {
		getPayments1().add(payments1);
		payments1.setInvoice1(this);

		return payments1;
	}

	public Payment removePayments1(Payment payments1) {
		getPayments1().remove(payments1);
		payments1.setInvoice1(null);

		return payments1;
	}

	public List<Payment> getPayments2() {
		return this.payments2;
	}

	public void setPayments2(List<Payment> payments2) {
		this.payments2 = payments2;
	}

	public Payment addPayments2(Payment payments2) {
		getPayments2().add(payments2);
		payments2.setInvoice2(this);

		return payments2;
	}

	public Payment removePayments2(Payment payments2) {
		getPayments2().remove(payments2);
		payments2.setInvoice2(null);

		return payments2;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setInvoice(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setInvoice(null);

		return report;
	}

	public List<TaxDetail> getTaxDetails1() {
		return this.taxDetails1;
	}

	public void setTaxDetails1(List<TaxDetail> taxDetails1) {
		this.taxDetails1 = taxDetails1;
	}

	public TaxDetail addTaxDetails1(TaxDetail taxDetails1) {
		getTaxDetails1().add(taxDetails1);
		taxDetails1.setInvoice1(this);

		return taxDetails1;
	}

	public TaxDetail removeTaxDetails1(TaxDetail taxDetails1) {
		getTaxDetails1().remove(taxDetails1);
		taxDetails1.setInvoice1(null);

		return taxDetails1;
	}

	public List<TaxDetail> getTaxDetails2() {
		return this.taxDetails2;
	}

	public void setTaxDetails2(List<TaxDetail> taxDetails2) {
		this.taxDetails2 = taxDetails2;
	}

	public TaxDetail addTaxDetails2(TaxDetail taxDetails2) {
		getTaxDetails2().add(taxDetails2);
		taxDetails2.setInvoice2(this);

		return taxDetails2;
	}

	public TaxDetail removeTaxDetails2(TaxDetail taxDetails2) {
		getTaxDetails2().remove(taxDetails2);
		taxDetails2.setInvoice2(null);

		return taxDetails2;
	}

	public int getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

}