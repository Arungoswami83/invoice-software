
package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the reports database table.
 * 
 */
@Entity
@Table(name="reports")
@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Temporal(TemporalType.DATE)
	@Column(name="issue_date")
	private Date issueDate;

	private int payments;

	@ManyToOne
    @JoinColumn(name = "sales_invoice_id")  
    private SalesInvoice salesInvoice;
	
	@ManyToOne
	@JoinColumn(name = "sales_invoices")  
	private SalesInvoices salesInvoices;

//
//	//bi-directional many-to-one association to Dashboard
//	@OneToMany(mappedBy="report")
//	private List<Dashboard> dashboards;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "invoice_id", nullable = false)
	private Invoice invoice;
	
	//bi-directional many-to-one association to InvoiceType
	@ManyToOne
	@JoinColumn(name="invoice_types")
	private InvoiceType invoiceType;

	public Report() {
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

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public int getPayments() {
		return this.payments;
	}

	public void setPayments(int payments) {
		this.payments = payments;
	}
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public SalesInvoice getSalesInvoice() {
		return salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

//
//	public Dashboard addDashboard(Dashboard dashboard) {
//		getDashboards().add(dashboard);
//		dashboard.setReport(this);
//
//		return dashboard;
//	}
//
//	public Dashboard removeDashboard(Dashboard dashboard) {
//		getDashboards().remove(dashboard);
//		dashboard.setReport(null);
//
//		return dashboard;
//	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public InvoiceType getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}
	@Override
	public String toString() {
		return "Report{" +
				"id=" + id +
				", createdAt=" + createdAt +
				", dueDate=" + dueDate +
				", issueDate=" + issueDate +
				", payments=" + payments +
				", salesInvoices=" + salesInvoice +
				'}';
	}

}
