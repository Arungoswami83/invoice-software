package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;

	private BigDecimal discount;

	@Column(name = "invoice_number", unique = true)
    private String invoiceNumber;
	
	@Column(name = "pdf_path")
	private String pdfPath;

	@Enumerated(EnumType.STRING)  
	 @Column(nullable = false)
	 private PaymentStatus paymentStatus; 
	 
//	@Column(name = "payment_method", length = 20)
//	private String paymentMethod;

	@Lob
	private int quantity;
	
	private String customerEmail;
	
    private String customerPhone;
    
    private String customerName;
	
	@Column(name="sub_total")
	private BigDecimal subTotal;
	
	@Column(name = "note")
    private String note;  

	private BigDecimal tax;
	
	private Date dueDate;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	@Column(name="updated_at")
	private LocalDateTime updatedAt;

	//bi-directional many-to-one association to Analytic
	@OneToMany(mappedBy="invoice")
	private List<Analytic> analytics;
	
	@Column(name = "paid")
	private BigDecimal paid;

	@Column(name = "balance")
	private BigDecimal balance;

	 @Enumerated(EnumType.STRING)
	 @Column(name = "category", columnDefinition = "ENUM('Service', 'Product', 'Other') DEFAULT 'Service'")
	 private Category category = Category.SERVICE;

	 @Column(name = "product_code")
	 private String productCode;
	    
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InvoiceHistory> invoiceHistories;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentHistory> paymentHistories;


//	//bi-directional many-to-one association to Dashboard
//	@OneToMany(mappedBy="invoice")
//	private List<Dashboard> dashboards;

	//bi-directional many-to-one association to EmailLog
	@OneToMany(mappedBy="invoice")
	private List<EmailLog> emailLogs;

	//bi-directional many-to-one association to Client
	@ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
	@JsonManagedReference 
    private Client client;

	//bi-directional many-to-one association to Company
	 @ManyToOne
	 @JoinColumn(name = "company_id", nullable = false)
	private Company company;

	//bi-directional many-to-one association to InvoiceType
	
	//bi-directional many-to-one association to InvoiceLog
	@OneToMany(mappedBy = "invoice")
	@JsonManagedReference
	private List<InvoiceLog> invoiceLogs;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="invoice")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;
	
	  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	  private List<Transaction> transactions;

	
	public BigDecimal getPaid() {
		return paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	//bi-directional many-to-one association to TaxDetail
	@OneToMany(mappedBy="invoice")
	private List<TaxDetail> taxDetails;
	
	 @Column(name="deleted")
	    private Boolean deleted;
	   
	public Boolean getDeleted() {
			return deleted;
		}

		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}

	public Invoice() {
	}	
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Date getDueDate() {
		return dueDate;
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	 @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.updatedAt = LocalDateTime.now();
	    }
	 public void generateInvoiceNumber() {
	        if (this.invoiceNumber == null) {
	            this.invoiceNumber = "INV-" + id; 
	        }
	    }

	    @PostPersist
	    public void updateInvoiceNumber() {
	        if (this.invoiceNumber == null || this.invoiceNumber.equals("INV-null")) {
	            this.invoiceNumber = "INV-" + id;
	        }
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }

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

	public List<Analytic> getAnalytics() {
		return this.analytics;
	}

	public void setAnalytics(List<Analytic> analytics) {
		this.analytics = analytics;
	}

	public Analytic addAnalytic(Analytic analytic) {
		getAnalytics().add(analytic);
		analytic.setInvoice(this);

		return analytic;
	}

	public Analytic removeAnalytic(Analytic analytic) {
		getAnalytics().remove(analytic);
		analytic.setInvoice(null);

		return analytic;
	}

	public List<InvoiceHistory> getInvoiceHistories() {
		return invoiceHistories;
	}

	public void setInvoiceHistories(List<InvoiceHistory> invoiceHistories) {
		this.invoiceHistories = invoiceHistories;
	}

	public List<PaymentHistory> getPaymentHistories() {
		return paymentHistories;
	}

	public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
		this.paymentHistories = paymentHistories;
	}

	public List<EmailLog> getEmailLogs() {
		return this.emailLogs;
	}

	public void setEmailLogs(List<EmailLog> emailLogs) {
		this.emailLogs = emailLogs;
	}

	public EmailLog addEmailLog(EmailLog emailLog) {
		getEmailLogs().add(emailLog);
		emailLog.setInvoice(this);

		return emailLog;
	}

	public EmailLog removeEmailLog(EmailLog emailLog) {
		getEmailLogs().remove(emailLog);
		emailLog.setInvoice(null);

		return emailLog;
	}
	
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<InvoiceLog> getInvoiceLogs() {
		return this.invoiceLogs;
	}

	public void setInvoiceLogs(List<InvoiceLog> invoiceLogs) {
		this.invoiceLogs = invoiceLogs;
	}

	public InvoiceLog addInvoiceLog(InvoiceLog invoiceLog) {
		getInvoiceLogs().add(invoiceLog);
		invoiceLog.setInvoice(this);

		return invoiceLog;
	}

	public InvoiceLog removeInvoiceLog(InvoiceLog invoiceLog) {
		getInvoiceLogs().remove(invoiceLog);
		invoiceLog.setInvoice(null);

		return invoiceLog;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setInvoice(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setInvoice(null);

		return notification;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setInvoice(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setInvoice(null);

		return payment;
	}

	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<TaxDetail> getTaxDetails() {
		return this.taxDetails;
	}

	public void setTaxDetails(List<TaxDetail> taxDetails) {
		this.taxDetails = taxDetails;
	}

	public TaxDetail addTaxDetail(TaxDetail taxDetail) {
		getTaxDetails().add(taxDetail);
		taxDetail.setInvoice(this);

		return taxDetail;
	}

	public TaxDetail removeTaxDetail(TaxDetail taxDetail) {
		getTaxDetails().remove(taxDetail);
		taxDetail.setInvoice(null);

		return taxDetail;
	}

	 public boolean isDeleted() {
	        return deleted;
	    }

	    public void setDeleted(boolean deleted) {
	        this.deleted = deleted;
	    }
	    

}