package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class StandardInvoiceResponseModel {
	private int id;
	private BigDecimal discount;
	private Date dueDate;
	private BigDecimal grandTotal;
	private Date invoiceDate;
	private boolean isRecurring;
	private String notes;
    private String paymentTerm;
	private String recurringFrequency;
	private byte sendEmail;
    private String status;
	private BigDecimal subtotal;
    private BigDecimal tax;
    
    
    
    


}
