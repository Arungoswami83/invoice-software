package com.amstech.invoice.service.converter.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.request.model.ProformaInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProformaInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.ProductInvoiceResponseModel;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;

@Component
public class ProformaInvoiceModelToEntityConverter {
	
	
	
	public ProformaInvoice getSignupConverter(ProformaInvoiceSignupRequestModel requestModel) {
		ProformaInvoice proformaInvoice = new ProformaInvoice();
	    proformaInvoice.setInvoiceNumber(requestModel.getInvoiceNumber());
	    proformaInvoice.setPaymentInstructions(requestModel.getPaymentInstructions());
	    proformaInvoice.setStatus(requestModel.getStatus());
	    proformaInvoice.setTotalAmount(requestModel.getTotalAmount());
	    proformaInvoice.setValidityPeriod(requestModel.getValidityPeriod());
	    proformaInvoice.setCompany(proformaInvoice.getCompany());
	    proformaInvoice.setClient(proformaInvoice.getClient());

	    return proformaInvoice;

}
	
	public ProformaInvoice getUpdateConverter(ProformaInvoiceUpdateRequestModel proformaInvoiceUpdateRequestModel,ProformaInvoice proformaInvoice) {
		proformaInvoice.setId(proformaInvoiceUpdateRequestModel.getId());
		proformaInvoice.setPaymentInstructions(proformaInvoiceUpdateRequestModel.getPaymentInstructions());
	    proformaInvoice.setStatus(proformaInvoiceUpdateRequestModel.getStatus());
	    proformaInvoice.setTotalAmount(proformaInvoiceUpdateRequestModel.getTotalAmount());
	    proformaInvoice.setValidityPeriod(proformaInvoiceUpdateRequestModel.getValidityPeriod());

	    
	    return proformaInvoice;
}


}
