package com.amstech.invoice.service.converter.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;
import com.amstech.invoice.service.entity.PaymentStatus;
import com.amstech.invoice.service.request.model.PaymentRequest;

@Component
public class PaymentModelToEntityConverter {

	  public static Payment addPayment(PaymentRequest paymentRequest,Optional <Invoice> optionalInvoice) {
	    	
		  Payment payment=new Payment();
	    	
		    payment.setInvoice(optionalInvoice.get()); 
		    payment.setAmountPaid(paymentRequest.getAmountPaid());
		    
		    if (paymentRequest.getPaymentDate() != null && !paymentRequest.getPaymentDate().isEmpty()) {
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        LocalDateTime paymentDate = LocalDateTime.parse(paymentRequest.getPaymentDate(), formatter);
		        payment.setPaymentDate(paymentDate);
		    }
		    
		    payment.setNotes(paymentRequest.getNotes());

		    payment.setPaymentMethod(PaymentMethod.valueOf(paymentRequest.getPaymentMethod().toUpperCase())); 
		    payment.setPaymentStatus(PaymentStatus.valueOf(paymentRequest.getPaymentStatus().toUpperCase()));

		    return payment;	

	    }
}