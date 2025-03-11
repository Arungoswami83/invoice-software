package com.amstech.invoice.service.entity;

import java.io.Serializable;
import java.sql.Date;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;
	
	 @Column(name="deleted")
	 private Boolean deleted;
	 
	  public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "client_id", nullable = false)  // Foreign Key in DB
	    private Client client;

	 public int getClientId() {
	        return invoice != null && invoice.getClient() != null ? invoice.getClient().getId() : null;
	    }

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@Column(name="date")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	//bi-directional many-to-one association to Analytic
	@OneToMany(mappedBy="generateInvoice")
	private List<Analytic> analytics;

	//bi-directional many-to-one association to Dashboard
	@OneToMany(mappedBy="generateInvoice")
	private List<Dashboard> dashboards;


	//bi-directional many-to-one association to Invoice
	@ManyToOne
	 @JoinColumn(name = "invoice_id")
	private Invoice invoice;

	 public GenerateInvoice(Invoice invoice) {
	        this.invoice = invoice;
	       // this.date = date.now();
	    }

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

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public String getInvoiceNumber() {
        return (invoice != null) ? invoice.getInvoiceNumber() : null;
    }

}