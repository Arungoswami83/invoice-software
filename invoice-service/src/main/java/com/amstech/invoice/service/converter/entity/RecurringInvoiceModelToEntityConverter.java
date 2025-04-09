package com.amstech.invoice.service.converter.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.request.model.RecurringInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.RecurringInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;
import com.amstech.invoice.service.response.model.RecurringInvoiceResponseModel;
@Component
public class RecurringInvoiceModelToEntityConverter {
	private ClientRepo clientRepo;
	private CompanyRepo companyRepo;
	public RecurringInvoice getsaveConverter(RecurringInvoiceSignupRequestModel recurringInvoiceSignupRequestModel) {
		RecurringInvoice recurringInvoice = new RecurringInvoice();
		recurringInvoice.setAutoPaymentSetup(recurringInvoiceSignupRequestModel.getAutoPaymentSetup());
		recurringInvoice.setEndDate(recurringInvoiceSignupRequestModel.getEndDate());
		recurringInvoice.setPaymentTerm(recurringInvoiceSignupRequestModel.getPaymentTerm());
		recurringInvoice.setTotalPayable(recurringInvoiceSignupRequestModel.getTotalPayable());


	    if (recurringInvoiceSignupRequestModel.getInvoiceNumber() == null || recurringInvoiceSignupRequestModel.getInvoiceNumber().isEmpty()) {
	        recurringInvoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
	    } else {
	        recurringInvoice.setInvoiceNumber(recurringInvoiceSignupRequestModel.getInvoiceNumber());
	    }

	    return recurringInvoice;
	}

     public RecurringInvoice getUpdateConverter(RecurringInvoiceUpdateRequestModel recurringInvoiceUpdateRequestModel,RecurringInvoice recurringInvoice) {
    	 
    	 recurringInvoice.setClient(recurringInvoice.getClient());
         recurringInvoice.setPaymentTerm(recurringInvoiceUpdateRequestModel.getPaymentTerm());
         recurringInvoice.setAutoPaymentSetup(recurringInvoiceUpdateRequestModel.getAutoPaymentSetup());
		return recurringInvoice;
     }


     
	
}
