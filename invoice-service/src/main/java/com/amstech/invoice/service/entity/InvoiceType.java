
package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the invoice_types database table.
 * 
 */
@Entity
@Table(name="invoice_types")
@NamedQuery(name="InvoiceType.findAll", query="SELECT i FROM InvoiceType i")
public class InvoiceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	//bi-directional many-to-one association to ProductInvoice
	@ManyToOne
	@JoinColumn(name="product_invoices_id")
	private ProductInvoice productInvoice;

	//bi-directional many-to-one association to ProformaInvoice
	@ManyToOne
	@JoinColumn(name="proforma_invoices_id")
	private ProformaInvoice proformaInvoice;

	//bi-directional many-to-one association to RecurringInvoice
	@ManyToOne
	@JoinColumn(name="recurring_invoices_id")
	private RecurringInvoice recurringInvoice;

	//bi-directional many-to-one association to ServiceInvoice
	@ManyToOne
	@JoinColumn(name="service_invoices_id")
	private ServiceInvoice serviceInvoice;

	//bi-directional many-to-one association to StandardInvoice
	@ManyToOne
	@JoinColumn(name="standard_invoices_id")
	private StandardInvoice standardInvoice;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="invoiceType")
	private List<Report> reports;

	public InvoiceType() {
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
	public ProductInvoice getProductInvoice() {
		return this.productInvoice;
	}

	public void setProductInvoice(ProductInvoice productInvoice) {
		this.productInvoice = productInvoice;
	}

	public ProformaInvoice getProformaInvoice() {
		return this.proformaInvoice;
	}

	public void setProformaInvoice(ProformaInvoice proformaInvoice) {
		this.proformaInvoice = proformaInvoice;
	}

	public RecurringInvoice getRecurringInvoice() {
		return this.recurringInvoice;
	}

	public void setRecurringInvoice(RecurringInvoice recurringInvoice) {
		this.recurringInvoice = recurringInvoice;
	}

	public ServiceInvoice getServiceInvoice() {
		return this.serviceInvoice;
	}

	public void setServiceInvoice(ServiceInvoice serviceInvoice) {
		this.serviceInvoice = serviceInvoice;
	}

	public StandardInvoice getStandardInvoice() {
		return this.standardInvoice;
	}

	public void setStandardInvoice(StandardInvoice standardInvoice) {
		this.standardInvoice = standardInvoice;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setInvoiceType(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setInvoiceType(null);

		return report;
	}

}
