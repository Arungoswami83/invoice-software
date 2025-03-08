package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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

	@Lob
	@Column(name="account_details")
	private String accountDetails;

	@Column(name="buyer_details")
	private String buyerDetails;

	@Temporal(TemporalType.DATE)
	private Date date;

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

	//bi-directional many-to-one association to ProductInvoiceItem
	@OneToMany(mappedBy="productInvoice1")
	private List<ProductInvoiceItem> productInvoiceItems1;

	//bi-directional many-to-one association to ProductInvoiceItem
	@OneToMany(mappedBy="productInvoice2")
	private List<ProductInvoiceItem> productInvoiceItems2;

	public ProductInvoice() {
	}

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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getHandlingCosts() {
		return this.handlingCosts;
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

	public List<ProductInvoiceItem> getProductInvoiceItems1() {
		return this.productInvoiceItems1;
	}

	public void setProductInvoiceItems1(List<ProductInvoiceItem> productInvoiceItems1) {
		this.productInvoiceItems1 = productInvoiceItems1;
	}

	public ProductInvoiceItem addProductInvoiceItems1(ProductInvoiceItem productInvoiceItems1) {
		getProductInvoiceItems1().add(productInvoiceItems1);
		productInvoiceItems1.setProductInvoice1(this);

		return productInvoiceItems1;
	}

	public ProductInvoiceItem removeProductInvoiceItems1(ProductInvoiceItem productInvoiceItems1) {
		getProductInvoiceItems1().remove(productInvoiceItems1);
		productInvoiceItems1.setProductInvoice1(null);

		return productInvoiceItems1;
	}

	public List<ProductInvoiceItem> getProductInvoiceItems2() {
		return this.productInvoiceItems2;
	}

	public void setProductInvoiceItems2(List<ProductInvoiceItem> productInvoiceItems2) {
		this.productInvoiceItems2 = productInvoiceItems2;
	}

	public ProductInvoiceItem addProductInvoiceItems2(ProductInvoiceItem productInvoiceItems2) {
		getProductInvoiceItems2().add(productInvoiceItems2);
		productInvoiceItems2.setProductInvoice2(this);

		return productInvoiceItems2;
	}

	public ProductInvoiceItem removeProductInvoiceItems2(ProductInvoiceItem productInvoiceItems2) {
		getProductInvoiceItems2().remove(productInvoiceItems2);
		productInvoiceItems2.setProductInvoice2(null);

		return productInvoiceItems2;
	}

}