package com.amstech.invoice.service.converter.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.response.model.ServiceInvoiceResponseModel;

@Component
public class ServiceInvoiceEntityToModelConverter {

	 public ServiceInvoiceResponseModel getFindByIdConverter(ServiceInvoice serviceInvoice) {
		   ServiceInvoiceResponseModel responseModel = new ServiceInvoiceResponseModel();
		   serviceInvoice.setId(serviceInvoice.getId());
		    responseModel.setInvoiceNumber(serviceInvoice.getInvoiceNumber());
		    responseModel.setGrandTotal(serviceInvoice.getGrandTotal());
		    responseModel.setNotes(serviceInvoice.getNotes());
		    responseModel.setPaymentTerm(serviceInvoice.getPaymentTerm());
		    responseModel.setStatus(serviceInvoice.getStatus());
		    responseModel.setSubTotal(serviceInvoice.getSubTotal());
		    responseModel.setTax(serviceInvoice.getTax());
		    

		    return responseModel;
		   
	   }
	   
	   public List<ServiceInvoiceResponseModel> getfindAllConverter(List<ServiceInvoice> serviceInvoiceList) {
  
		   List<ServiceInvoiceResponseModel> responseList = new ArrayList<>();

		    for (ServiceInvoice invoice : serviceInvoiceList) {
		        ServiceInvoiceResponseModel responseModel = new ServiceInvoiceResponseModel();
		        responseModel.setId(invoice.getId());
		        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
		        responseModel.setGrandTotal(invoice.getGrandTotal());
		        responseModel.setNotes(invoice.getNotes());
		        responseModel.setPaymentTerm(invoice.getPaymentTerm());
		        responseModel.setStatus(invoice.getStatus());
		        responseModel.setSubTotal(invoice.getSubTotal());
		        responseModel.setTax(invoice.getTax());
		         responseList.add(responseModel);
		    }

		    return responseList;

		   
	   }
}
