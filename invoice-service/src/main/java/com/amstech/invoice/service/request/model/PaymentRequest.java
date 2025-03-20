package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequest {
	private Integer invoiceId;
    private BigDecimal amountPaid;
    private String paymentDate;  
    private String notes;
    private String paymentMethod;
    private String paymentStatus;
   }
