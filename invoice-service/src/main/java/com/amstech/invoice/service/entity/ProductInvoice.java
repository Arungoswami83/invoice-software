package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The persistent class for the product_invoices database table.
 * 
 */
@Entity
@Table(name = "product_invoices")
@NamedQuery(name = "ProductInvoice.findAll", query = "SELECT p FROM ProductInvoice p")
public class ProductInvoice implements Serializable {
    public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "account_details")
    private String accountDetails;

    @Column(name = "buyer_details")
    private String buyerDetails;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "handling_costs")
    private BigDecimal handlingCosts;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String pdfPath;

    @Column(name = "payment_term")
    private String paymentTerm;

    private BigDecimal shipping;

    private String supplier;

    @Column(name = "tax_calculation")
    private BigDecimal taxCalculation;

    @Column(name = "total_payable")
    private BigDecimal totalPayable;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    
    @Column(name = "grand_total")
    private BigDecimal grandTotal;


    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "status")
    private String status;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "paid")
    private BigDecimal paid;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "category")
    private String category;

    @Column(name = "note")
    private String note;

    @Column(name = "payment_status")
    private String paymentStatus;

    

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "productInvoice")
    private List<InvoiceType> invoiceTypes;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "productInvoice")
    private List<ProductInvoiceItem> productInvoiceItems;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(String accountDetails) {
        this.accountDetails = accountDetails;
    }

    public String getBuyerDetails() {
        return buyerDetails;
    }

    public void setBuyerDetails(String buyerDetails) {
        this.buyerDetails = buyerDetails;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getHandlingCosts() {
        return handlingCosts;
    }

    public void setHandlingCosts(BigDecimal handlingCosts) {
        this.handlingCosts = handlingCosts;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public BigDecimal getShipping() {
        return shipping;
    }

    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getTaxCalculation() {
        return taxCalculation;
    }

    public void setTaxCalculation(BigDecimal taxCalculation) {
        this.taxCalculation = taxCalculation;
    }

    public BigDecimal getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(BigDecimal totalPayable) {
        this.totalPayable = totalPayable;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public List<InvoiceType> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<InvoiceType> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

    public InvoiceType addInvoiceType(InvoiceType invoiceType) {
        getInvoiceTypes().add(invoiceType);
        invoiceType.setProductInvoice(this);
        return invoiceType;
    }

    public InvoiceType removeInvoiceType(InvoiceType invoiceType) {
        getInvoiceTypes().remove(invoiceType);
        invoiceType.setProductInvoice(null);
        return invoiceType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ProductInvoiceItem> getProductInvoiceItems() {
        return productInvoiceItems;
    }

    public void setProductInvoiceItems(List<ProductInvoiceItem> productInvoiceItems) {
        this.productInvoiceItems = productInvoiceItems;
    }

    public ProductInvoiceItem addProductInvoiceItem(ProductInvoiceItem productInvoiceItem) {
        getProductInvoiceItems().add(productInvoiceItem);
        productInvoiceItem.setProductInvoice(this);
        return productInvoiceItem;
    }

    public ProductInvoiceItem removeProductInvoiceItem(ProductInvoiceItem productInvoiceItem) {
        getProductInvoiceItems().remove(productInvoiceItem);
        productInvoiceItem.setProductInvoice(null);
        return productInvoiceItem;
    }
}
