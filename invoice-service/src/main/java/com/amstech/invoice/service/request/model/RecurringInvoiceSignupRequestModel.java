package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
@Data
public class RecurringInvoiceSignupRequestModel {
	
	private int id;
    private byte autoPaymentSetup;

	private Integer companyId;

	private Integer clientId;
    private Date endDate;
    private String paymentTerm;
    private Date startDate;
    private BigDecimal totalPayable;
    }
