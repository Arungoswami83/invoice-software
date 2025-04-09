package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ServiceInvoiceResponseModels {
	    private int id;
	    private Date dueDate;
	    private BigDecimal grandTotal;
	    private String invoiceNumber;
	    private String notes;
	    private String paymentTerm;
	    private String status;
	    private BigDecimal subTotal;
	    private BigDecimal tax;
	    
	    private List<ClientResponseModel> clientService;
	     

}
