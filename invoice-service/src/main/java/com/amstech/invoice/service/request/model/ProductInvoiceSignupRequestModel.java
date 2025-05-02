package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
@Data
public class ProductInvoiceSignupRequestModel {
	private Integer id;
	private String paymentMethod;
	private Date dueDate;
	private Integer isDeleted;
	private String invoiceNumber;
	private String pdfPath;
	private BigDecimal totalAmount;
	private String status;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal tax;
	private BigDecimal grandTotal;
	private BigDecimal paid;
	private BigDecimal balance;
	private Integer quantity;
	private String customerEmail;
	private String customerName;
	private String customerPhone;
	private String category;
	private String note;
	private String paymentStatus;
	private Integer clientId;
	private Integer companyId;
	private String accountDetails;
	private String buyerDetails;
	private BigDecimal handlingCosts;
	private String orderNumber;
	private String paymentTerm;
	private BigDecimal shipping;
	private String supplier;
	private BigDecimal taxCalculation;
	private BigDecimal totalPayable;


		



}
