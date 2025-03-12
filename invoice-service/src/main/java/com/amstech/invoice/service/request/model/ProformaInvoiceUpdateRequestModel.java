package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProformaInvoiceUpdateRequestModel {
	private int id;
    private String paymentInstructions;
    private String status;
    private BigDecimal totalAmount;
	private String validityPeriod;

   
}
