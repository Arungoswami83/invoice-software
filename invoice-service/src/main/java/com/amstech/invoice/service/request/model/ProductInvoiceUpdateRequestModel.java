package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
@Data
public class ProductInvoiceUpdateRequestModel {
	private int id;
	private String accountDetails;
	private String buyerDetails;
	private long dueDate;
    private String supplier;
	private String orderNumber;
	private String paymentMethod;
	private String paymentTerm;
	private BigDecimal shipping;
	private BigDecimal taxCalculation;
	private BigDecimal totalPayable;
	
	


}
