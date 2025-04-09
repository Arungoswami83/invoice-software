package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
@Data
public class ProductInvoiceSignupRequestModel {
	private String accountDetails;
	private String buyerDetails;
	private Date dueDate;
	private BigDecimal handlingCosts;
	private String orderNumber;
	private String paymentMethod;
	private String paymentTerm;
	private BigDecimal shipping;
	private String supplier;
	private BigDecimal taxCalculation;
	private BigDecimal totalPayable;
    private String invoiceNumber;


		



}
