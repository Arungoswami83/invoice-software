package com.amstech.invoice.service.converter.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.request.model.ServiceInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ServiceInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.RecurringInvoiceResponseModel;
import com.amstech.invoice.service.response.model.ServiceInvoiceResponseModel;

import ch.qos.logback.core.net.server.Client;

@Component
public class ServiceInvoiceModelToEntityConverter {

	public ServiceInvoice getsaveConverter(ServiceInvoiceSignupRequestModel serviceInvoiceSignupRequestModel) {
	    ServiceInvoice serviceInvoice = new ServiceInvoice();
	    serviceInvoice.setInvoiceNumber(serviceInvoiceSignupRequestModel.getInvoiceNumber());
	    serviceInvoice.setDueDate(serviceInvoiceSignupRequestModel.getDueDate());
	    serviceInvoice.setGrandTotal(serviceInvoiceSignupRequestModel.getGrandTotal());
	    serviceInvoice.setNotes(serviceInvoiceSignupRequestModel.getNotes());
	    serviceInvoice.setPaymentTerm(serviceInvoiceSignupRequestModel.getPaymentTerm());
	    serviceInvoice.setSubTotal(serviceInvoiceSignupRequestModel.getSubTotal());
	    serviceInvoice.setTax(serviceInvoiceSignupRequestModel.getTax());
	    serviceInvoice.setStatus(serviceInvoiceSignupRequestModel.getStatus());
	    serviceInvoice.setClient(serviceInvoice.getClient());
	    if (serviceInvoiceSignupRequestModel.getInvoiceNumber() == null || serviceInvoiceSignupRequestModel.getInvoiceNumber().isEmpty()) {
	    	serviceInvoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        } else {
        	serviceInvoice.setInvoiceNumber(serviceInvoiceSignupRequestModel.getInvoiceNumber());
        }
	    return serviceInvoice;
	

	}

	public ServiceInvoice getUpdateConverter(ServiceInvoiceUpdateRequestModel serviceInvoiceUpdateRequestModel,ServiceInvoice serviceInvoice) {
	    serviceInvoice.setGrandTotal(serviceInvoiceUpdateRequestModel.getGrandTotal());
	    serviceInvoice.setNotes(serviceInvoiceUpdateRequestModel.getNotes());
	    serviceInvoice.setPaymentTerm(serviceInvoiceUpdateRequestModel.getPaymentTerm());
	    serviceInvoice.setSubTotal(serviceInvoiceUpdateRequestModel.getSubTotal());
	    serviceInvoice.setTax(serviceInvoiceUpdateRequestModel.getTax());
	    serviceInvoice.setStatus(serviceInvoiceUpdateRequestModel.getStatus());

	    return serviceInvoice;
	

	}

	  }
