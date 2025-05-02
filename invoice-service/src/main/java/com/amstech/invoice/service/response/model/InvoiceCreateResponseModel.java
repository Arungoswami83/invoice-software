package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class InvoiceCreateResponseModel {

	private int id;
	private String invoiceNumber;
	private Date issueDate;
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
	
	private List<ClientResponseModel>clientResponseMosdels;
	private List<CompanyResponseModel>companyResponseModels;
}
