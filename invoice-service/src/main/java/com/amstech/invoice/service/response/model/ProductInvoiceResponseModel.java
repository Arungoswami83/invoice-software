package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
@Data
public class ProductInvoiceResponseModel {
	private Integer  id;
	private String buyerDetails;
	private String orderNumber;
	private String paymentMethod;
	private String supplier;
	private String invoiceNumber;
	private String pdfPath;
	


}
