package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the proforma_invoices database table.
 * 
 */
@Entity
@Table(name="proforma_invoices")
@NamedQuery(name="ProformaInvoice.findAll", query="SELECT p FROM ProformaInvoice p")
public class ProformaInvoice implements Serializable {
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

	@Column(name="invoice_number")
	private String invoiceNumber;

	@Lob
	@Column(name="payment_instructions")
	private String paymentInstructions;

	private String status;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="validity_period")
	private String validityPeriod;

	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="proformaInvoice")
	private List<InvoiceType> invoiceTypes;

	//bi-directional many-to-one association to ProformaInvoiceItem
	@OneToMany(mappedBy="proformaInvoice1")
	private List<ProformaInvoiceItem> proformaInvoiceItems1;

	//bi-directional many-to-one association to ProformaInvoiceItem
	@OneToMany(mappedBy="proformaInvoice2")
	private List<ProformaInvoiceItem> proformaInvoiceItems2;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="company_id1")
	private Company company1;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="company_id2")
	private Company company2;

	public ProformaInvoice() {
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

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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

	public List<ProformaInvoiceItem> getProformaInvoiceItems1() {
		return this.proformaInvoiceItems1;
	}

	public void setProformaInvoiceItems1(List<ProformaInvoiceItem> proformaInvoiceItems1) {
		this.proformaInvoiceItems1 = proformaInvoiceItems1;
	}

	public ProformaInvoiceItem addProformaInvoiceItems1(ProformaInvoiceItem proformaInvoiceItems1) {
		getProformaInvoiceItems1().add(proformaInvoiceItems1);
		proformaInvoiceItems1.setProformaInvoice1(this);

		return proformaInvoiceItems1;
	}

	public ProformaInvoiceItem removeProformaInvoiceItems1(ProformaInvoiceItem proformaInvoiceItems1) {
		getProformaInvoiceItems1().remove(proformaInvoiceItems1);
		proformaInvoiceItems1.setProformaInvoice1(null);

		return proformaInvoiceItems1;
	}

	public List<ProformaInvoiceItem> getProformaInvoiceItems2() {
		return this.proformaInvoiceItems2;
	}

	public void setProformaInvoiceItems2(List<ProformaInvoiceItem> proformaInvoiceItems2) {
		this.proformaInvoiceItems2 = proformaInvoiceItems2;
	}

	public ProformaInvoiceItem addProformaInvoiceItems2(ProformaInvoiceItem proformaInvoiceItems2) {
		getProformaInvoiceItems2().add(proformaInvoiceItems2);
		proformaInvoiceItems2.setProformaInvoice2(this);

		return proformaInvoiceItems2;
	}

	public ProformaInvoiceItem removeProformaInvoiceItems2(ProformaInvoiceItem proformaInvoiceItems2) {
		getProformaInvoiceItems2().remove(proformaInvoiceItems2);
		proformaInvoiceItems2.setProformaInvoice2(null);

		return proformaInvoiceItems2;
	}

	public Company getCompany1() {
		return this.company1;
	}

	public void setCompany1(Company company1) {
		this.company1 = company1;
	}

	public Company getCompany2() {
		return this.company2;
	}

	public void setCompany2(Company company2) {
		this.company2 = company2;
	}

}