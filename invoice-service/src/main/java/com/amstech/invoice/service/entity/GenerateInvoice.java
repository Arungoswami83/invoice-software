package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the generate_invoice database table.
 * 
 */
@Entity
@Table(name="generate_invoice")
@NamedQuery(name="GenerateInvoice.findAll", query="SELECT g FROM GenerateInvoice g")
public class GenerateInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Analytic
	@OneToMany(mappedBy="generateInvoice")
	private List<Analytic> analytics;

	//bi-directional many-to-one association to Dashboard
	@OneToMany(mappedBy="generateInvoice")
	private List<Dashboard> dashboards;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	private Invoice invoice;

	public GenerateInvoice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Analytic> getAnalytics() {
		return this.analytics;
	}

	public void setAnalytics(List<Analytic> analytics) {
		this.analytics = analytics;
	}

	public Analytic addAnalytic(Analytic analytic) {
		getAnalytics().add(analytic);
		analytic.setGenerateInvoice(this);

		return analytic;
	}

	public Analytic removeAnalytic(Analytic analytic) {
		getAnalytics().remove(analytic);
		analytic.setGenerateInvoice(null);

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
		dashboard.setGenerateInvoice(this);

		return dashboard;
	}

	public Dashboard removeDashboard(Dashboard dashboard) {
		getDashboards().remove(dashboard);
		dashboard.setGenerateInvoice(null);

		return dashboard;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}