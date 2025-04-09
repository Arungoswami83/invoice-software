package com.amstech.invoice.service.converter.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;
import com.amstech.invoice.service.entity.PaymentStatus;
import com.amstech.invoice.service.request.model.PaymentRequest;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;

@Component
public class PaymentEntityToModelConverter {
	
	public PaymentResponseMoodel addPayment(Payment payment) {
    	PaymentResponseMoodel responseMoodel=new PaymentResponseMoodel();
    	
    	responseMoodel.setId(payment.getId());
    	responseMoodel.setAmountPaid(payment.getAmountPaid());
    	responseMoodel.setNotes(payment.getNotes());
    	responseMoodel.setInvoiceId(payment.getInvoice().getId());
    	responseMoodel.setAmountPaid(payment.getAmountPaid());
    	 if (payment.getPaymentDate() != null) {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
             responseMoodel.setPaymentDate(payment.getPaymentDate().format(formatter));
         }    	responseMoodel.setPaymentMethod(payment.getPaymentMethod().name()); 
    	responseMoodel.setPaymentStatus(payment.getPaymentStatus().name()); 
          
    	return responseMoodel;
    }
	public List<PaymentResponseMoodel> convertToResponseModel(List<Payment> payments) {
	    List<PaymentResponseMoodel> responseList = new ArrayList<>();
	    
	    for (Payment payment : payments) {
	        PaymentResponseMoodel responseModel = addPayment(payment);
	        responseList.add(responseModel);
	    }
	    
	    return responseList;
	}
	
}
