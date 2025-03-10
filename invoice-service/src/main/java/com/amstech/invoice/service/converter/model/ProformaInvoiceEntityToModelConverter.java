package com.amstech.invoice.service.converter.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;

@Component
public class ProformaInvoiceEntityToModelConverter {
	
	public ProformaInvoiceResponseModel getfindbyid(ProformaInvoice proformaInvoice) throws Exception {

		 ProformaInvoiceResponseModel response = new ProformaInvoiceResponseModel();
		    response.setId(proformaInvoice.getId());
		    response.setPaymentInstructions(proformaInvoice.getPaymentInstructions());
		    response.setStatus(proformaInvoice.getStatus());
		    response.setTotalAmount(proformaInvoice.getTotalAmount());
		    response.setValidityPeriod(proformaInvoice.getValidityPeriod());

		    return response;
	}
	public List<ProformaInvoiceResponseModel> getfindAllConverter(List<ProformaInvoice> proformaInvoiceList) {
	    List<ProformaInvoiceResponseModel> responseList = new ArrayList<>();

		for (ProformaInvoice invoice : proformaInvoiceList) {
	        ProformaInvoiceResponseModel responseModel = new ProformaInvoiceResponseModel();
	        responseModel.setId(invoice.getId());
	        responseModel.setPaymentInstructions(invoice.getPaymentInstructions());
	        responseModel.setStatus(invoice.getStatus());
	        responseModel.setTotalAmount(invoice.getTotalAmount());
	        responseModel.setValidityPeriod(invoice.getValidityPeriod());
	        responseList.add(responseModel);
	    }

	    return responseList;
	}
	

}
