package com.amstech.invoice.service.converter.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.SalesInvoices;
import com.amstech.invoice.service.response.model.CompanyResponseModel;
import com.amstech.invoice.service.response.model.SalesInvoiceResponseModel;
@Component
public class SalesEntityToModelConverter {
	public SalesInvoiceResponseModel getfindBySalesInvoiceId(SalesInvoices sales) {
    	
    
        SalesInvoiceResponseModel responseModel = new SalesInvoiceResponseModel();
        
        responseModel.setId(sales.getId());
        responseModel.setPaymentTerm(sales.getPaymentTerm());
        responseModel.setSignature(sales.getSignature());
        responseModel.setSubtotal(sales.getSubtotal());
        responseModel.setTax(sales.getTax());
        responseModel.setTotal(sales.getTotal());
        responseModel.setDiscount(sales.getDiscount());
        responseModel.setClientId(sales.getClient().getId());
        responseModel.setPrice(sales.getPrice());
        responseModel.setStatus(sales.getStatus());
        responseModel.setInvoiceNumber(sales.getInvoiceNumber());
        return responseModel;
    }

    public List<SalesInvoiceResponseModel> getfindAllActiveConverter(List<SalesInvoices> salesInvoiceList) {
    	
    	  List<SalesInvoiceResponseModel> responseModels = new ArrayList<>();
	        
	        for (SalesInvoices sales : salesInvoiceList) {
	        	
	        SalesInvoiceResponseModel responseModel = new SalesInvoiceResponseModel();
	        	  
	            responseModel.setId(sales.getId());
	            responseModel.setDiscount(sales.getDiscount());
	            responseModel.setPaymentTerm(sales.getPaymentTerm());
	            responseModel.setSignature(sales.getSignature());
	            responseModel.setSubtotal(sales.getSubtotal());
	            responseModel.setTax(sales.getTax());
	            responseModel.setTotal(sales.getTotal());
	            responseModel.setDiscount(sales.getDiscount());
	            responseModel.setPrice(sales.getPrice());
	            responseModels.add(responseModel);
	            responseModel.setStatus(sales.getStatus());
	            responseModel.setInvoiceNumber(sales.getInvoiceNumber());

	        }
			return responseModels;
    }

}
