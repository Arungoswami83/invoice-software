package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class RecurringInvoiceResponseModel {
	private int id;
    private byte autoPaymentSetup;
	private String paymentTerm;
	private Date endDate;
    private BigDecimal totalPayable;

}
