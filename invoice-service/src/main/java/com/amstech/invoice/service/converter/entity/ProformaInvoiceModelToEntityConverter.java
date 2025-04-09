package com.amstech.invoice.service.converter.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	    proformaInvoice.setPaymentInstructions(requestModel.getPaymentInstructions());
	    proformaInvoice.setStatus(requestModel.getStatus());
	    proformaInvoice.setTotalAmount(requestModel.getTotalAmount());
	    proformaInvoice.setValidityPeriod(requestModel.getValidityPeriod());
	    proformaInvoice.setCompany(proformaInvoice.getCompany());
	    proformaInvoice.setClient(proformaInvoice.getClient());

	    //  Auto-generate Invoice Number if missing
        if (requestModel.getInvoiceNumber() == null || requestModel.getInvoiceNumber().isEmpty()) {
        	proformaInvoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        } else {
        	proformaInvoice.setInvoiceNumber(requestModel.getInvoiceNumber());
        }

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
