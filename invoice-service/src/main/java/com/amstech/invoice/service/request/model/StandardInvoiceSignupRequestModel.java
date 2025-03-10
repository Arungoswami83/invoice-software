package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
@Data
public class StandardInvoiceSignupRequestModel {
	private int id;
	private BigDecimal discount;
	private Date dueDate;
	private BigDecimal grandTotal;
	private Date invoiceDate;
	private String invoiceNumber;
	private boolean isRecurring;
	private String notes;
    private String paymentTerm;
	private String recurringFrequency;
	private byte sendEmail;
    private String status;
	private BigDecimal subtotal;
    private BigDecimal tax;
     private Integer companyId;
	private Integer clientId;
	
	
	
	
	
	
	

}
