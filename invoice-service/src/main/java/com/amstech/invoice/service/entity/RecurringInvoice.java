package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the recurring_invoices database table.
 * 
 */
@Entity
@Table(name="recurring_invoices")
@NamedQuery(name="RecurringInvoice.findAll", query="SELECT r FROM RecurringInvoice r")
public class RecurringInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="auto_payment_setup")
	private byte autoPaymentSetup;

	@Lob
	@Column(name="client_address")
	private String clientAddress;

	@Column(name="client_email")
	private String clientEmail;

	@Column(name="client_name")
	private String clientName;

	@Column(name="client_phone")
	private String clientPhone;

	@Column(name="company_info")
	private String companyInfo;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="payment_term")
	private String paymentTerm;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="total_payable")
	private BigDecimal totalPayable;

	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="recurringInvoice")
	private List<InvoiceType> invoiceTypes;

	//bi-directional many-to-one association to RecurringInvoiceItem
	@OneToMany(mappedBy="recurringInvoice1")
	private List<RecurringInvoiceItem> recurringInvoiceItems1;

	//bi-directional many-to-one association to RecurringInvoiceItem
	@OneToMany(mappedBy="recurringInvoice2")
	private List<RecurringInvoiceItem> recurringInvoiceItems2;

	//bi-directional many-to-one association to RecurringService
	@OneToMany(mappedBy="recurringInvoice1")
	private List<RecurringService> recurringServices1;

	//bi-directional many-to-one association to RecurringService
	@OneToMany(mappedBy="recurringInvoice2")
	private List<RecurringService> recurringServices2;

	public RecurringInvoice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAutoPaymentSetup() {
		return this.autoPaymentSetup;
	}

	public void setAutoPaymentSetup(byte autoPaymentSetup) {
		this.autoPaymentSetup = autoPaymentSetup;
	}

	public String getClientAddress() {
		return this.clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientEmail() {
		return this.clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPhone() {
		return this.clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getCompanyInfo() {
		return this.companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getTotalPayable() {
		return this.totalPayable;
	}

	public void setTotalPayable(BigDecimal totalPayable) {
		this.totalPayable = totalPayable;
	}

	public List<InvoiceType> getInvoiceTypes() {
		return this.invoiceTypes;
	}

	public void setInvoiceTypes(List<InvoiceType> invoiceTypes) {
		this.invoiceTypes = invoiceTypes;
	}

	public InvoiceType addInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().add(invoiceType);
		invoiceType.setRecurringInvoice(this);

		return invoiceType;
	}

	public InvoiceType removeInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().remove(invoiceType);
		invoiceType.setRecurringInvoice(null);

		return invoiceType;
	}

	public List<RecurringInvoiceItem> getRecurringInvoiceItems1() {
		return this.recurringInvoiceItems1;
	}

	public void setRecurringInvoiceItems1(List<RecurringInvoiceItem> recurringInvoiceItems1) {
		this.recurringInvoiceItems1 = recurringInvoiceItems1;
	}

	public RecurringInvoiceItem addRecurringInvoiceItems1(RecurringInvoiceItem recurringInvoiceItems1) {
		getRecurringInvoiceItems1().add(recurringInvoiceItems1);
		recurringInvoiceItems1.setRecurringInvoice1(this);

		return recurringInvoiceItems1;
	}

	public RecurringInvoiceItem removeRecurringInvoiceItems1(RecurringInvoiceItem recurringInvoiceItems1) {
		getRecurringInvoiceItems1().remove(recurringInvoiceItems1);
		recurringInvoiceItems1.setRecurringInvoice1(null);

		return recurringInvoiceItems1;
	}

	public List<RecurringInvoiceItem> getRecurringInvoiceItems2() {
		return this.recurringInvoiceItems2;
	}

	public void setRecurringInvoiceItems2(List<RecurringInvoiceItem> recurringInvoiceItems2) {
		this.recurringInvoiceItems2 = recurringInvoiceItems2;
	}

	public RecurringInvoiceItem addRecurringInvoiceItems2(RecurringInvoiceItem recurringInvoiceItems2) {
		getRecurringInvoiceItems2().add(recurringInvoiceItems2);
		recurringInvoiceItems2.setRecurringInvoice2(this);

		return recurringInvoiceItems2;
	}

	public RecurringInvoiceItem removeRecurringInvoiceItems2(RecurringInvoiceItem recurringInvoiceItems2) {
		getRecurringInvoiceItems2().remove(recurringInvoiceItems2);
		recurringInvoiceItems2.setRecurringInvoice2(null);

		return recurringInvoiceItems2;
	}

	public List<RecurringService> getRecurringServices1() {
		return this.recurringServices1;
	}

	public void setRecurringServices1(List<RecurringService> recurringServices1) {
		this.recurringServices1 = recurringServices1;
	}

	public RecurringService addRecurringServices1(RecurringService recurringServices1) {
		getRecurringServices1().add(recurringServices1);
		recurringServices1.setRecurringInvoice1(this);

		return recurringServices1;
	}

	public RecurringService removeRecurringServices1(RecurringService recurringServices1) {
		getRecurringServices1().remove(recurringServices1);
		recurringServices1.setRecurringInvoice1(null);

		return recurringServices1;
	}

	public List<RecurringService> getRecurringServices2() {
		return this.recurringServices2;
	}

	public void setRecurringServices2(List<RecurringService> recurringServices2) {
		this.recurringServices2 = recurringServices2;
	}

	public RecurringService addRecurringServices2(RecurringService recurringServices2) {
		getRecurringServices2().add(recurringServices2);
		recurringServices2.setRecurringInvoice2(this);

		return recurringServices2;
	}

	public RecurringService removeRecurringServices2(RecurringService recurringServices2) {
		getRecurringServices2().remove(recurringServices2);
		recurringServices2.setRecurringInvoice2(null);

		return recurringServices2;
	}

}