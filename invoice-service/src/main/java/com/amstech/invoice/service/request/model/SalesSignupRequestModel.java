package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
@Data
public class SalesSignupRequestModel {
	
	  
    private String invoiceNumber;  
    private int clientId;   
    private String paymentTerm;  
    private BigDecimal subtotal; 
    private BigDecimal discount;  
    private BigDecimal tax;  
    private BigDecimal total;  
    private String signature;  
   private double price;
   private String status;
  }
