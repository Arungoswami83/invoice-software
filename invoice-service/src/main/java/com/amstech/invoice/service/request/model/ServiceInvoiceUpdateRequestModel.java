package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
@Data
public class ServiceInvoiceUpdateRequestModel {
	private int id;
  
	private String notes;
    private String paymentTerm;
    
    private String status;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;
    
}
