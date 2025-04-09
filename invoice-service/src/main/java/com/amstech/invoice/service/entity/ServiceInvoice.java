package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

	@ManyToOne
	@JoinColumn(name="client_id",referencedColumnName = "id")
	private Client client;


	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="grand_total")
	private BigDecimal grandTotal;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Column(name = "pdf_path")
	private String pdfPath;

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	@Lob
	private String notes;

	@Column(name="payment_term")
	private String paymentTerm;

	private String signature;

	
	@Column(name="is_deleted",nullable=false)
	private int isDeleted;

	private String status;

	@Column(name="sub_total")
	private BigDecimal subTotal;

	private BigDecimal tax;

	@Column(name="updated_at")
	private Timestamp updatedAt;



	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="serviceInvoice")
	private List<InvoiceType> invoiceTypes;

	@OneToMany(mappedBy="serviceInvoice")
	private List<ServiceDetail> serviceDetails;

	public ServiceInvoice() {
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
	
	
	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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

	public List<ServiceDetail> getServiceDetails() {
		return this.serviceDetails;
	}

	public void setServiceDetails(List<ServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public ServiceDetail addServiceDetail(ServiceDetail serviceDetail) {
		getServiceDetails().add(serviceDetail);
		serviceDetail.setServiceInvoice(this);

		return serviceDetail;
	}

	public ServiceDetail removeServiceDetail(ServiceDetail serviceDetail) {
		getServiceDetails().remove(serviceDetail);
		serviceDetail.setServiceInvoice(null);

		return serviceDetail;
	}

}