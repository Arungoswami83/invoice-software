package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="auto_payment_setup")
	private byte autoPaymentSetup;

	
	@ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;




	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;



	@Column(name="payment_term")
	private String paymentTerm;


	@Column(name="total_payable")
	private BigDecimal totalPayable;

	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="recurringInvoice")
	private List<InvoiceType> invoiceTypes;

	@OneToMany(mappedBy="recurringInvoice")
	private List<RecurringInvoiceItem> recurringInvoiceItems;

	//bi-directional many-to-one association to RecurringInvoicePayment
	@OneToMany(mappedBy="recurringInvoice")
	private List<RecurringInvoicePayment> recurringInvoicePayments;

	//bi-directional many-to-one association to RecurringService
	@OneToMany(mappedBy="recurringInvoice")
	private List<RecurringService> recurringServices;

	public RecurringInvoice() {
	}
	@Column(name="is_deleted")
	private int isDeleted;

	public int getIsDeleted() {
		return isDeleted;
	}
	 @CreationTimestamp
	  @Column(updatable = false, nullable = false)
	    private LocalDateTime createdAt;

	    public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
		@UpdateTimestamp
	    @Column(nullable = false)
	    private LocalDateTime updatedAt;

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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

	public List<RecurringInvoiceItem> getRecurringInvoiceItems() {
		return this.recurringInvoiceItems;
	}

	public void setRecurringInvoiceItems(List<RecurringInvoiceItem> recurringInvoiceItems) {
		this.recurringInvoiceItems = recurringInvoiceItems;
	}

	public RecurringInvoiceItem addRecurringInvoiceItem(RecurringInvoiceItem recurringInvoiceItem) {
		getRecurringInvoiceItems().add(recurringInvoiceItem);
		recurringInvoiceItem.setRecurringInvoice(this);

		return recurringInvoiceItem;
	}

	public RecurringInvoiceItem removeRecurringInvoiceItem(RecurringInvoiceItem recurringInvoiceItem) {
		getRecurringInvoiceItems().remove(recurringInvoiceItem);
		recurringInvoiceItem.setRecurringInvoice(null);

		return recurringInvoiceItem;
	}

	public List<RecurringInvoicePayment> getRecurringInvoicePayments() {
		return this.recurringInvoicePayments;
	}

	public void setRecurringInvoicePayments(List<RecurringInvoicePayment> recurringInvoicePayments) {
		this.recurringInvoicePayments = recurringInvoicePayments;
	}

	public RecurringInvoicePayment addRecurringInvoicePayment(RecurringInvoicePayment recurringInvoicePayment) {
		getRecurringInvoicePayments().add(recurringInvoicePayment);
		recurringInvoicePayment.setRecurringInvoice(this);

		return recurringInvoicePayment;
	}

	public RecurringInvoicePayment removeRecurringInvoicePayment(RecurringInvoicePayment recurringInvoicePayment) {
		getRecurringInvoicePayments().remove(recurringInvoicePayment);
		recurringInvoicePayment.setRecurringInvoice(null);

		return recurringInvoicePayment;
	}

	public List<RecurringService> getRecurringServices() {
		return this.recurringServices;
	}

	public void setRecurringServices(List<RecurringService> recurringServices) {
		this.recurringServices = recurringServices;
	}

	public RecurringService addRecurringService(RecurringService recurringService) {
		getRecurringServices().add(recurringService);
		recurringService.setRecurringInvoice(this);

		return recurringService;
	}

	public RecurringService removeRecurringService(RecurringService recurringService) {
		getRecurringServices().remove(recurringService);
		recurringService.setRecurringInvoice(null);

		return recurringService;
	}
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


}