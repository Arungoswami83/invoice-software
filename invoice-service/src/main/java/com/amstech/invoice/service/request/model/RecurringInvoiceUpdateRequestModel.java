package com.amstech.invoice.service.request.model;


import lombok.Data;

@Data
public class RecurringInvoiceUpdateRequestModel {
	
	    private int id;
        private byte autoPaymentSetup;
	    private String paymentTerm;
	    

}
