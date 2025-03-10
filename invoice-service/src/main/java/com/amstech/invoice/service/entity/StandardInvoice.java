package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the standard_invoices database table.
 * 
 */
@Entity
@Table(name="standard_invoices")
@NamedQuery(name="StandardInvoice.findAll", query="SELECT s FROM StandardInvoice s")
public class StandardInvoice implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;


	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="is_deleted")
	private int isDeleted;

	private BigDecimal discount;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="grand_total")
	private BigDecimal grandTotal;

	@Temporal(TemporalType.DATE)
	@Column(name="invoice_date")
	private Date invoiceDate;

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Column(name="is_recurring")
	private boolean isRecurring;

	@Lob
	private String notes;

	@Column(name="payment_term")
	private String paymentTerm;

	@Column(name="recurring_frequency")
	private String recurringFrequency;

	@Column(name="send_email")
	private byte sendEmail;

	private String status;

	private BigDecimal subtotal;

	private BigDecimal tax;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="standardInvoice")
	private List<InvoiceType> invoiceTypes;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	public StandardInvoice() {
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

	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public BigDecimal getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public boolean isRecurring() {
		return isRecurring;
	}

	public void setRecurring(boolean isRecurring) {
		this.isRecurring = isRecurring;
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

	public String getRecurringFrequency() {
		return this.recurringFrequency;
	}

	public void setRecurringFrequency(String recurringFrequency) {
		this.recurringFrequency = recurringFrequency;
	}

	public byte getSendEmail() {
		return this.sendEmail;
	}

	public void setSendEmail(byte sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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
		invoiceType.setStandardInvoice(this);

		return invoiceType;
	}

	public InvoiceType removeInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().remove(invoiceType);
		invoiceType.setStandardInvoice(null);

		return invoiceType;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}