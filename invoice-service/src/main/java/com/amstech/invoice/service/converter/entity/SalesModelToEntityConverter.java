package com.amstech.invoice.service.converter.entity;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.SalesInvoices;
import com.amstech.invoice.service.request.model.SalesSignupRequestModel;
import com.amstech.invoice.service.request.model.SalesUpdateRequestModel;
import com.amstech.invoice.service.response.model.SalesInvoiceResponseModel;

@Component
public class SalesModelToEntityConverter {
	
	  public SalesInvoices getsave(SalesSignupRequestModel salesSignupRequestModel) throws Exception {


		   SalesInvoices salesInvoices = new SalesInvoices();
	        salesInvoices.setInvoiceNumber(salesSignupRequestModel.getInvoiceNumber());
	        salesInvoices.setDiscount(salesSignupRequestModel.getDiscount());
	        salesInvoices.setPaymentTerm(salesSignupRequestModel.getPaymentTerm());
	        salesInvoices.setSignature(salesSignupRequestModel.getSignature());
	        salesInvoices.setStatus(salesSignupRequestModel.getStatus());
	        salesInvoices.setSubtotal(salesSignupRequestModel.getSubtotal());
	        salesInvoices.setTax(salesSignupRequestModel.getTax());
	        salesInvoices.setTotal(salesSignupRequestModel.getTotal());
	        salesInvoices.setClient(salesInvoices.getClient());
	  
	        return salesInvoices;
	  }
	  public SalesInvoices getupdate(SalesUpdateRequestModel salesUpdateRequestModel ,SalesInvoices salesInvoices) throws Exception {

		  salesInvoices.setPaymentTerm(salesUpdateRequestModel.getPaymentTerm());
	        salesInvoices.setSignature(salesUpdateRequestModel.getSignature());
	        salesInvoices.setStatus(salesUpdateRequestModel.getStatus());
	        salesInvoices.setSubtotal(salesUpdateRequestModel.getSubtotal());
	        salesInvoices.setTotal(salesUpdateRequestModel.getTotal());
	        salesInvoices.setTax(salesUpdateRequestModel.getTax());
	        
	        return salesInvoices;
	  
}
}
