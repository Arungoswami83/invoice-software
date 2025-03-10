package com.amstech.invoice.service.converter.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.response.model.StandardInvoiceResponseModel;

@Component
public class StandardInvoiceEntityToModelConverter {
	public StandardInvoiceResponseModel findInvoiceById(StandardInvoice standardInvoice) {
		
		  StandardInvoiceResponseModel responseModel = new StandardInvoiceResponseModel();
		    responseModel.setId(standardInvoice.getId());
		    responseModel.setInvoiceDate(standardInvoice.getInvoiceDate());
		    responseModel.setDueDate(standardInvoice.getDueDate());
		    responseModel.setDiscount(standardInvoice.getDiscount());
		    responseModel.setGrandTotal(standardInvoice.getGrandTotal());
		    responseModel.setNotes(standardInvoice.getNotes());
		    responseModel.setPaymentTerm(standardInvoice.getPaymentTerm());
		    responseModel.setRecurringFrequency(standardInvoice.getRecurringFrequency());
		    standardInvoice.setRecurring("true".equalsIgnoreCase(standardInvoice.getRecurringFrequency()));
		    responseModel.setSendEmail(standardInvoice.getSendEmail());
		    responseModel.setStatus(standardInvoice.getStatus());
		    responseModel.setSubtotal(standardInvoice.getSubtotal());
		    responseModel.setTax(standardInvoice.getTax());

		    return responseModel;
	}
	
	public List<StandardInvoiceResponseModel> findAllInvoices(List<StandardInvoice> standardInvoiceList) {
	    List<StandardInvoiceResponseModel> responseList = new ArrayList<>();

	    for (StandardInvoice invoice : standardInvoiceList) {
	        StandardInvoiceResponseModel responseModel = new StandardInvoiceResponseModel();
	        responseModel.setId(invoice.getId());
	        responseModel.setInvoiceDate(invoice.getInvoiceDate());
	        responseModel.setDueDate(invoice.getDueDate());
	        responseModel.setDiscount(invoice.getDiscount());
	        responseModel.setGrandTotal(invoice.getGrandTotal());
	        responseModel.setNotes(invoice.getNotes());
	        responseModel.setPaymentTerm(invoice.getPaymentTerm());
	        responseModel.setRecurringFrequency(invoice.getRecurringFrequency());

	        // ✅ Corrected line
	        responseModel.setRecurring("true".equalsIgnoreCase(invoice.getRecurringFrequency())); 
	        
	        responseModel.setSendEmail(invoice.getSendEmail());
	        responseModel.setStatus(invoice.getStatus());
	        responseModel.setSubtotal(invoice.getSubtotal());
	        responseModel.setTax(invoice.getTax());

	        responseList.add(responseModel);
	    }

	    return responseList;
	}


}
