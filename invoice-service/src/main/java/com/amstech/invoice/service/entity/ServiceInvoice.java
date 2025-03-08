package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the service_invoices database table.
 * 
 */
@Entity
@Table(name="service_invoices")
@NamedQuery(name="ServiceInvoice.findAll", query="SELECT s FROM ServiceInvoice s")
public class ServiceInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="client_id")
	private int clientId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="grand_total")
	private BigDecimal grandTotal;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Lob
	private String notes;

	@Column(name="payment_term")
	private String paymentTerm;

	private String signature;

	private String status;

	@Column(name="sub_total")
	private BigDecimal subTotal;

	private BigDecimal tax;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="serviceInvoice")
	private List<InvoiceType> invoiceTypes;

	//bi-directional many-to-one association to ServiceDetail
	@OneToMany(mappedBy="serviceInvoice1")
	private List<ServiceDetail> serviceDetails1;

	//bi-directional many-to-one association to ServiceDetail
	@OneToMany(mappedBy="serviceInvoice2")
	private List<ServiceDetail> serviceDetails2;

	public ServiceInvoice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
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

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<InvoiceType> getInvoiceTypes() {
		return this.invoiceTypes;
	}

	public void setInvoiceTypes(List<InvoiceType> invoiceTypes) {
		this.invoiceTypes = invoiceTypes;
	}

	public InvoiceType addInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().add(invoiceType);
		invoiceType.setServiceInvoice(this);

		return invoiceType;
	}

	public InvoiceType removeInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().remove(invoiceType);
		invoiceType.setServiceInvoice(null);

		return invoiceType;
	}

	public List<ServiceDetail> getServiceDetails1() {
		return this.serviceDetails1;
	}

	public void setServiceDetails1(List<ServiceDetail> serviceDetails1) {
		this.serviceDetails1 = serviceDetails1;
	}

	public ServiceDetail addServiceDetails1(ServiceDetail serviceDetails1) {
		getServiceDetails1().add(serviceDetails1);
		serviceDetails1.setServiceInvoice1(this);

		return serviceDetails1;
	}

	public ServiceDetail removeServiceDetails1(ServiceDetail serviceDetails1) {
		getServiceDetails1().remove(serviceDetails1);
		serviceDetails1.setServiceInvoice1(null);

		return serviceDetails1;
	}

	public List<ServiceDetail> getServiceDetails2() {
		return this.serviceDetails2;
	}

	public void setServiceDetails2(List<ServiceDetail> serviceDetails2) {
		this.serviceDetails2 = serviceDetails2;
	}

	public ServiceDetail addServiceDetails2(ServiceDetail serviceDetails2) {
		getServiceDetails2().add(serviceDetails2);
		serviceDetails2.setServiceInvoice2(this);

		return serviceDetails2;
	}

	public ServiceDetail removeServiceDetails2(ServiceDetail serviceDetails2) {
		getServiceDetails2().remove(serviceDetails2);
		serviceDetails2.setServiceInvoice2(null);

		return serviceDetails2;
	}

}