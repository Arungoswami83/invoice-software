package com.amstech.invoice.service.converter.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.request.model.RecurringInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.RecurringInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;
import com.amstech.invoice.service.response.model.RecurringInvoiceResponseModel;
@Component
public class RecurringInvoiceModelToEntityConverter {
	
	public RecurringInvoice getsaveConverter(RecurringInvoiceSignupRequestModel recurringInvoiceSignupRequestModel) {
	
        RecurringInvoice recurringInvoice = new RecurringInvoice();
        recurringInvoice.setAutoPaymentSetup(recurringInvoiceSignupRequestModel.getAutoPaymentSetup());
        recurringInvoice.setEndDate(recurringInvoiceSignupRequestModel.getEndDate());
        recurringInvoice.setPaymentTerm(recurringInvoiceSignupRequestModel.getPaymentTerm());
        recurringInvoice.setTotalPayable(recurringInvoiceSignupRequestModel.getTotalPayable());
   	    recurringInvoice.setClient(recurringInvoice.getClient());
   	    recurringInvoice.setCompany(recurringInvoice.getCompany());
        return recurringInvoice;
      
	
	}
     public RecurringInvoice getUpdateConverter(RecurringInvoiceUpdateRequestModel recurringInvoiceUpdateRequestModel,RecurringInvoice recurringInvoice) {
    	 
    	 recurringInvoice.setClient(recurringInvoice.getClient());
         recurringInvoice.setPaymentTerm(recurringInvoiceUpdateRequestModel.getPaymentTerm());
         recurringInvoice.setAutoPaymentSetup(recurringInvoiceUpdateRequestModel.getAutoPaymentSetup());
		return recurringInvoice;
     }


     
	
}
