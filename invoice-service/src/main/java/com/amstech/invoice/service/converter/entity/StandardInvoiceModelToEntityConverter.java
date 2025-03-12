package com.amstech.invoice.service.converter.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.request.model.ServiceInvoiceUpdateRequestModel;
import com.amstech.invoice.service.request.model.StandardInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.StandardInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.StandardInvoiceResponseModel;

@Component
public class StandardInvoiceModelToEntityConverter {
	public StandardInvoice getsaveConverter(StandardInvoiceSignupRequestModel standardInvoiceSignupRequestModel) {

		

	    StandardInvoice standardInvoice = new StandardInvoice();
	    standardInvoice.setClient(standardInvoice.getClient());
	    standardInvoice.setCompany(standardInvoice.getCompany());
        standardInvoice.setInvoiceNumber(standardInvoiceSignupRequestModel.getInvoiceNumber());
	    standardInvoice.setDueDate(standardInvoiceSignupRequestModel.getDueDate());
	    standardInvoice.setSubtotal(standardInvoiceSignupRequestModel.getSubtotal());
	    standardInvoice.setDiscount(standardInvoiceSignupRequestModel.getDiscount());
	    standardInvoice.setTax(standardInvoiceSignupRequestModel.getTax());
	    standardInvoice.setGrandTotal(standardInvoiceSignupRequestModel.getGrandTotal());
	    standardInvoice.setPaymentTerm(standardInvoiceSignupRequestModel.getPaymentTerm());
	    standardInvoice.setNotes(standardInvoiceSignupRequestModel.getNotes());
	    standardInvoice.setStatus(standardInvoiceSignupRequestModel.getStatus());
	    standardInvoice.setRecurringFrequency(standardInvoiceSignupRequestModel.getRecurringFrequency());
	    standardInvoice.setSendEmail(standardInvoiceSignupRequestModel.getSendEmail());
	    standardInvoice.setRecurring("true".equalsIgnoreCase(standardInvoiceSignupRequestModel.getRecurringFrequency()));
	    return standardInvoice;
	}
	
	public StandardInvoice getUpdateConverter(StandardInvoiceUpdateRequestModel requestModel,StandardInvoice standardInvoice) {

	    standardInvoice.setDiscount(requestModel.getDiscount());
	    standardInvoice.setDueDate(requestModel.getDueDate());
	    standardInvoice.setGrandTotal(requestModel.getGrandTotal());
	    standardInvoice.setRecurring("true".equalsIgnoreCase(requestModel.getRecurringFrequency()));
	    standardInvoice.setNotes(requestModel.getNotes());
	    standardInvoice.setPaymentTerm(requestModel.getPaymentTerm());
	    standardInvoice.setRecurringFrequency(requestModel.getRecurringFrequency());
	    standardInvoice.setSendEmail(requestModel.getSendEmail());
	    standardInvoice.setStatus(requestModel.getStatus());
	    standardInvoice.setSubtotal(requestModel.getSubtotal());
	    standardInvoice.setTax(requestModel.getTax());

		return standardInvoice;
		
	}
	

	}
