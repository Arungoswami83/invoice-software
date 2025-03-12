package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;

import com.amstech.invoice.service.entity.Client;

import lombok.Data;
@Data
public class SalesInvoiceResponseModel {
	private int id; 
    private String paymentTerm;  
    private BigDecimal subtotal; 
    private BigDecimal discount; 
    private BigDecimal tax;  
    private BigDecimal total;
    private String signature;
    private String statusName;
    private int clientId;
}
