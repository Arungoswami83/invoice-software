package com.amstech.invoice.service.converter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.response.model.StandardInvoiceResponseModel;

@Component
public class StandardInvoiceEntityToModelConverter {
	public StandardInvoiceResponseModel getfindInvoiceById(StandardInvoice standardInvoice) {
		
		  StandardInvoiceResponseModel responseModel = new StandardInvoiceResponseModel();
		    responseModel.setId(standardInvoice.getId());
		    responseModel.setDiscount(standardInvoice.getDiscount());
		    responseModel.setGrandTotal(standardInvoice.getGrandTotal());
		    responseModel.setNotes(standardInvoice.getNotes());
		    responseModel.setPaymentTerm(standardInvoice.getPaymentTerm());
		    responseModel.setRecurringFrequency(standardInvoice.getRecurringFrequency());
		    responseModel.setStatus(standardInvoice.getStatus());
		    responseModel.setSubtotal(standardInvoice.getSubtotal());
		    responseModel.setTax(standardInvoice.getTax());
		    responseModel.setInvoiceNumber(standardInvoice.getInvoiceNumber());
		    return responseModel;
	}
	
	public List<StandardInvoiceResponseModel> findAllInvoices(List<StandardInvoice> standardInvoiceList) {
	    List<StandardInvoiceResponseModel> responseList = new ArrayList<>();

	    for (StandardInvoice invoice : standardInvoiceList) {
	        StandardInvoiceResponseModel responseModel = new StandardInvoiceResponseModel();
	        responseModel.setId(invoice.getId());
	        responseModel.setDiscount(invoice.getDiscount());
	        responseModel.setGrandTotal(invoice.getGrandTotal());
	        responseModel.setNotes(invoice.getNotes());
	        responseModel.setPaymentTerm(invoice.getPaymentTerm());
	        responseModel.setRecurringFrequency(invoice.getRecurringFrequency());
	        responseModel.setStatus(invoice.getStatus());
	        responseModel.setSubtotal(invoice.getSubtotal());
	        responseModel.setTax(invoice.getTax());
	        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
	        responseList.add(responseModel);
	    }

	    return responseList;
	}


}
