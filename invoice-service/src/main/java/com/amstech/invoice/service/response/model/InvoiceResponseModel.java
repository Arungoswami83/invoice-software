package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class InvoiceResponseModel  {
	
	private int id;
	private String invoiceNumber;
	private LocalDateTime createdAt;
	private Date dueDate;
	private BigDecimal totalAmount;
	private String status;
	private String PdfUrl;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal tax;
	private BigDecimal grandTotal;
	private int quantity;
	private String productCode;
	private String paymentMethod;
	private String paymentStatus;
	private BigDecimal balance;
	private BigDecimal paid;
	private String category;


}



