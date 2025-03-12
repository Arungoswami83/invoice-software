package com.amstech.invoice.service.converter.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.request.model.ProductInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProductInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.ProductInvoiceResponseModel;

@Component
public class ProductInvoiceModelToEntityConverter {
	
	public ProductInvoice getSignupConverter(ProductInvoiceSignupRequestModel productInvoiceSignupRequestModel) {
		
		ProductInvoice productInvoice = new ProductInvoice();
		


		    productInvoice.setAccountDetails(productInvoiceSignupRequestModel.getAccountDetails());
		    productInvoice.setBuyerDetails(productInvoiceSignupRequestModel.getBuyerDetails());
		    productInvoice.setDueDate(new Date(productInvoiceSignupRequestModel.getDueDate()));
            productInvoice.setHandlingCosts(productInvoiceSignupRequestModel.getHandlingCosts());
		    productInvoice.setOrderNumber(productInvoiceSignupRequestModel.getOrderNumber());
		    productInvoice.setPaymentMethod(productInvoiceSignupRequestModel.getPaymentMethod());
		    productInvoice.setPaymentTerm(productInvoiceSignupRequestModel.getPaymentTerm());
		    productInvoice.setShipping(productInvoiceSignupRequestModel.getShipping());
		    productInvoice.setSupplier(productInvoiceSignupRequestModel.getSupplier());
		    productInvoice.setTaxCalculation(productInvoiceSignupRequestModel.getTaxCalculation());
		    productInvoice.setTotalPayable(productInvoiceSignupRequestModel.getTotalPayable());
		return productInvoice;
		
	}
	
	public ProductInvoice getUpdateConverter(ProductInvoiceUpdateRequestModel productInvoiceUpdateRequestModel,ProductInvoice productInvoice) {
		productInvoice.setId(productInvoiceUpdateRequestModel.getId());
		productInvoice.setAccountDetails(productInvoiceUpdateRequestModel.getAccountDetails());
		productInvoice.setBuyerDetails(productInvoiceUpdateRequestModel.getBuyerDetails());
	    productInvoice.setDueDate(new Date(productInvoiceUpdateRequestModel.getDueDate()));
        productInvoice.setSupplier(productInvoiceUpdateRequestModel.getSupplier());
		productInvoice.setOrderNumber(productInvoiceUpdateRequestModel.getOrderNumber());
	    productInvoice.setPaymentMethod(productInvoiceUpdateRequestModel.getPaymentMethod());
	    productInvoice.setPaymentTerm(productInvoiceUpdateRequestModel.getPaymentTerm());
	    productInvoice.setShipping(productInvoiceUpdateRequestModel.getShipping());
	    productInvoice.setTaxCalculation(productInvoiceUpdateRequestModel.getTaxCalculation());
	    productInvoice.setTotalPayable(productInvoiceUpdateRequestModel.getTotalPayable());
		return productInvoice;
	}
	
	



}
