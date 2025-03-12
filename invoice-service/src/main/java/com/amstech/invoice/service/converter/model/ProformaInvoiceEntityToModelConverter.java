package com.amstech.invoice.service.converter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;

@Component
public class ProformaInvoiceEntityToModelConverter {
	private int id;
	private String invoiceNumber;
 	private String paymentInstructions;
    private String status;
	private BigDecimal totalAmount;
	private String validityPeriod;

	public ProformaInvoiceResponseModel getfindbyid(ProformaInvoice proformaInvoice) throws Exception {

		 ProformaInvoiceResponseModel response = new ProformaInvoiceResponseModel();
		    response.setId(proformaInvoice.getId());
		    response.setPaymentInstructions(proformaInvoice.getPaymentInstructions());
		    response.setStatus(proformaInvoice.getStatus());
		    response.setTotalAmount(proformaInvoice.getTotalAmount());
		    response.setValidityPeriod(proformaInvoice.getValidityPeriod());
		    response.setInvoiceNumber(proformaInvoice.getInvoiceNumber());
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
	        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
	        responseList.add(responseModel);
	    }

	    return responseList;
	}
	

}
