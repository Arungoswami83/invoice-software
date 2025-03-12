package com.amstech.invoice.service.entity;

import java.io.Serializable;
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
	@JoinColumn(name="sales_invoices_id")
	private SalesInvoices salesInvoices;
	
	 @ManyToOne
	    @JoinColumn(name = "sales_invoice_id")  // Foreign Key
	    private SalesInvoice salesInvoice; 


	//bi-directional many-to-one association to Dashboard
	@OneToMany(mappedBy="analytic")
	private List<Dashboard> dashboards;
	public Analytic() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	


	public SalesInvoices getSalesInvoices() {
		return salesInvoices;
	}

	public void setSalesInvoices(SalesInvoices salesInvoices) {
		this.salesInvoices = salesInvoices;
	}

	public List<Dashboard> getDashboards() {
		return this.dashboards;
	}

	public void setDashboards(List<Dashboard> dashboards) {
		this.dashboards = dashboards;
	}

	public Dashboard addDashboard(Dashboard dashboard) {
		getDashboards().add(dashboard);
		dashboard.setAnalytic(this);

		return dashboard;
	}

	public Dashboard removeDashboard(Dashboard dashboard) {
		getDashboards().remove(dashboard);
		dashboard.setAnalytic(null);

		return dashboard;
	}

}