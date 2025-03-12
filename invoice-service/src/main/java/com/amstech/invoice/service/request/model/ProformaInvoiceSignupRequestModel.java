package com.amstech.invoice.service.request.model;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import lombok.Data;
@Data
public class ProformaInvoiceSignupRequestModel {
	private String invoiceNumber;
    private String paymentInstructions;
    private String status;
    private BigDecimal totalAmount;
    private String validityPeriod;
    private Integer companyId;
    private Integer clientId;

	


}
