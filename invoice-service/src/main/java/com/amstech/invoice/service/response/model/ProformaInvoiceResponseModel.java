package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProformaInvoiceResponseModel {
	
	private int id;
	private String invoiceNumber;
 	private String paymentInstructions;
    private String status;
	private BigDecimal totalAmount;
	private String validityPeriod;

}