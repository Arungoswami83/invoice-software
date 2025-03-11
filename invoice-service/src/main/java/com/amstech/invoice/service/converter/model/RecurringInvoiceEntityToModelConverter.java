package com.amstech.invoice.service.converter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.response.model.RecurringInvoiceResponseModel;

@Component
public class RecurringInvoiceEntityToModelConverter {
	   
    public RecurringInvoiceResponseModel getFindByIdConverter(RecurringInvoice recurringInvoice) {
      	 
    	

	    RecurringInvoiceResponseModel responseModel = new RecurringInvoiceResponseModel();
	    responseModel.setId(recurringInvoice.getId());
	    responseModel.setAutoPaymentSetup(recurringInvoice.getAutoPaymentSetup());
	    responseModel.setPaymentTerm(recurringInvoice.getPaymentTerm());
	    responseModel.setEndDate(recurringInvoice.getEndDate());
	    responseModel.setTotalPayable(recurringInvoice.getTotalPayable());
	    return responseModel;
    

}
  public List<RecurringInvoiceResponseModel> getfindAllConverter(List<RecurringInvoice> recurringInvoicesList) {
      
      List<RecurringInvoiceResponseModel> responseList = new ArrayList<>();
      for (RecurringInvoice invoice : recurringInvoicesList) {
          RecurringInvoiceResponseModel responseModel = new RecurringInvoiceResponseModel();
        responseModel.setId(invoice.getId());
  	    responseModel.setAutoPaymentSetup(invoice.getAutoPaymentSetup());
  	    responseModel.setPaymentTerm(invoice.getPaymentTerm());
  	    responseModel.setEndDate(invoice.getEndDate());
  	    responseModel.setTotalPayable(invoice.getTotalPayable());
         responseList.add(responseModel);
      }
      return responseList;
	}
	

}
