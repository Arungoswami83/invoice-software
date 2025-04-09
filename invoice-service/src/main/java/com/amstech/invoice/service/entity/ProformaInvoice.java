package com.amstech.invoice.service.entity;

import java.io.Serializable;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The persistent class for the proforma_invoices database table.
 * 
 */
@Entity
@Table(name = "proforma_invoices")
@NamedQuery(name = "ProformaInvoice.findAll", query = "SELECT p FROM ProformaInvoice p")
public class ProformaInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@Column(name = "invoice_number")
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
	@Column(name = "payment_instructions")
	private String paymentInstructions;

	private String status;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

//	@PrePersist
//	protected void onCreate() {
//		this.createdAt = LocalDateTime.now();
//		this.updatedAt = LocalDateTime.now();
//	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	@Column(name = "validity_period")
	private String validityPeriod;

	// bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy = "proformaInvoice")
	private List<InvoiceType> invoiceTypes;

	// bi-directional many-to-one association to ProformaInvoiceItem

	public ProformaInvoice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "proformaInvoice")
	private List<ProformaInvoiceItem> proformaInvoiceItems;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Column(name = "is_deleted")
	private int isDeleted;

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPaymentInstructions() {
		return this.paymentInstructions;
	}

	public void setPaymentInstructions(String paymentInstructions) {
		this.paymentInstructions = paymentInstructions;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	

	public String getValidityPeriod() {
		return this.validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public List<InvoiceType> getInvoiceTypes() {
		return this.invoiceTypes;
	}

	public void setInvoiceTypes(List<InvoiceType> invoiceTypes) {
		this.invoiceTypes = invoiceTypes;
	}

	public InvoiceType addInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().add(invoiceType);
		invoiceType.setProformaInvoice(this);

		return invoiceType;
	}

	public InvoiceType removeInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().remove(invoiceType);
		invoiceType.setProformaInvoice(null);

		return invoiceType;
	}

	public List<ProformaInvoiceItem> getProformaInvoiceItems() {
		return this.proformaInvoiceItems;
	}

	public void setProformaInvoiceItems(List<ProformaInvoiceItem> proformaInvoiceItems) {
		this.proformaInvoiceItems = proformaInvoiceItems;
	}

	public ProformaInvoiceItem addProformaInvoiceItem(ProformaInvoiceItem proformaInvoiceItem) {
		getProformaInvoiceItems().add(proformaInvoiceItem);
		proformaInvoiceItem.setProformaInvoice(this);

		return proformaInvoiceItem;
	}

	public ProformaInvoiceItem removeProformaInvoiceItem(ProformaInvoiceItem proformaInvoiceItem) {
		getProformaInvoiceItems().remove(proformaInvoiceItem);
		proformaInvoiceItem.setProformaInvoice(null);

		return proformaInvoiceItem;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@PrePersist
	public void prePersist() {
	    LocalDateTime now = LocalDateTime.now();
	    this.createdAt = now;
	    this.updatedAt = now;

	    // Generate invoice number only if it's null
	    if (this.invoiceNumber == null) {
	        this.invoiceNumber = "INV-" + System.currentTimeMillis(); // Unique invoice number
	    }
	}

	

}