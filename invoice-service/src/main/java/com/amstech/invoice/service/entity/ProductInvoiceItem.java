package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the product_invoice_items database table.
 * 
 */
@Entity
@Table(name="product_invoice_items")
@NamedQuery(name="ProductInvoiceItem.findAll", query="SELECT p FROM ProductInvoiceItem p")
public class ProductInvoiceItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	private String name;

	private int quantity;

	@Column(name="sub_total")
	private BigDecimal subTotal;

	@Column(name="unit_price")
	private BigDecimal unitPrice;


	@JoinColumn(name="invoice_id")
	private ProductInvoice productInvoice;

	public ProductInvoiceItem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	ProductInvoice getProductInvoice() {
		return this.productInvoice;
	}

	public void setProductInvoice(ProductInvoice productInvoice) {
		this.productInvoice = productInvoice;

	}

}