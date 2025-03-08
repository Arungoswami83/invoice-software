package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
@Data
public class SalesUpdateRequestModel {
	private int id;  
    private String paymentTerm;  
    private BigDecimal subtotal;  
    private BigDecimal discount; 
    private BigDecimal tax; 
    private BigDecimal total; 
    private String signature; 
    private int clientId;
    private String status;
    
   }
