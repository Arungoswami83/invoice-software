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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	



	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="grand_total")
	private BigDecimal grandTotal;


    @Column(name = "invoice_number", unique = true, nullable = false)
    private String invoiceNumber;

    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


	@Lob
	private String notes;

	@Column(name="payment_term")
	private String paymentTerm;

	private String signature;

	 @ManyToOne
	    @JoinColumn(name = "client_id",nullable = false)
	    private Client client; // 🔥 client_id NULL होने से इशू आ सकता है
	
	public void setClient(Client client) {
		this.client = client;
	}
	

	public Client getClient() {
		return client;
	}


	private String status;

	@Column(name="sub_total")
	private BigDecimal subTotal;

	private BigDecimal tax;
	
	
	@Column(name="is_deleted")
	private int isDeleted;

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="serviceInvoice")
	private List<InvoiceType> invoiceTypes;

	//bi-directional many-to-one association to ServiceDetail
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