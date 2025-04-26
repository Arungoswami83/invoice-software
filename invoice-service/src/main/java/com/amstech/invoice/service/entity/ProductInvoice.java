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
@Table(name="product_invoices")
@NamedQuery(name="ProductInvoice.findAll", query="SELECT p FROM ProductInvoice p")
public class ProductInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "pdf_path")
	private String pdfPath;
	
	@Lob
	@Column(name="account_details")
	private String accountDetails;

	@Column(name="buyer_details")
	private String buyerDetails;

@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="handling_costs")
	private BigDecimal handlingCosts;

	@Column(name="order_number")
	private String orderNumber;

	@Column(name="payment_method")
	private String paymentMethod;

	@Column(name="payment_term")
	private String paymentTerm;

	private BigDecimal shipping;

	private String supplier;

	@Column(name="tax_calculation")
	private BigDecimal taxCalculation;

	@Column(name="total_payable")
	private BigDecimal totalPayable;

	//bi-directional many-to-one association to InvoiceType
	@OneToMany(mappedBy="productInvoice")
	private List<InvoiceType> invoiceTypes;

	@OneToMany(mappedBy="productInvoice")
	private List<ProductInvoiceItem> productInvoiceItems;
	
	@Column(name="is_deleted")
	private int isDeleted;

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ProductInvoice() {
	}
       @CreationTimestamp
	    @Column(updatable = false, nullable = false)
	    private LocalDateTime createdAt;

	    @UpdateTimestamp
	    @Column(nullable = false)
	    private LocalDateTime updatedAt;

	    @Column(name = "invoice_number", unique = true)
	    private String invoiceNumber;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountDetails() {
		return this.accountDetails;
	}

	public void setAccountDetails(String accountDetails) {
		this.accountDetails = accountDetails;
	}

	public String getBuyerDetails() {
		return this.buyerDetails;
	}

	public void setBuyerDetails(String buyerDetails) {
		this.buyerDetails = buyerDetails;
	}


	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	
	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public BigDecimal getHandlingCosts() {
		return handlingCosts;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public void setHandlingCosts(BigDecimal handlingCosts) {
		this.handlingCosts = handlingCosts;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public BigDecimal getShipping() {
		return this.shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public BigDecimal getTaxCalculation() {
		return this.taxCalculation;
	}

	public void setTaxCalculation(BigDecimal taxCalculation) {
		this.taxCalculation = taxCalculation;
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
		invoiceType.setProductInvoice(this);

		return invoiceType;
	}

	public InvoiceType removeInvoiceType(InvoiceType invoiceType) {
		getInvoiceTypes().remove(invoiceType);
		invoiceType.setProductInvoice(null);

		return invoiceType;
	}


	public List<ProductInvoiceItem> getProductInvoiceItems() {
		return this.productInvoiceItems;
	}

	public void setProductInvoiceItems(List<ProductInvoiceItem> productInvoiceItems) {
		this.productInvoiceItems = productInvoiceItems;
	}
	

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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