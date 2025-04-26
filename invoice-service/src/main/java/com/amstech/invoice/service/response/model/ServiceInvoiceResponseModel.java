package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.google.protobuf.Timestamp;

import lombok.Data;
@Data
public class ServiceInvoiceResponseModel {
	
	
	
	    private int id;
	    private String invoiceNumber;
   	    private BigDecimal grandTotal;
	    private String notes;
	    private String paymentTerm;
	    private String status;
	    private BigDecimal subTotal;
	    private BigDecimal tax;
	    private String pdfPath;
	  

}
