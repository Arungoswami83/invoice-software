package com.amstech.invoice.service.converter.entity;

import java.util.ArrayList;
import java.util.List;

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
