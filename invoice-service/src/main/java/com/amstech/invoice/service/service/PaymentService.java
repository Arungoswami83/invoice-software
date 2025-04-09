package com.amstech.invoice.service.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amstech.invoice.service.converter.entity.PaymentModelToEntityConverter;
import com.amstech.invoice.service.converter.model.PaymentEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;
import com.amstech.invoice.service.entity.PaymentStatus;
import com.amstech.invoice.service.repo.InvoiceRepo;
import com.amstech.invoice.service.repo.PaymentRepo;
import com.amstech.invoice.service.request.model.PaymentRequest;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;

@Service
public class PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Autowired
	private PaymentEntityToModelConverter paymentEntityToModelConverter;
	
	@Transactional
	public PaymentResponseMoodel addPayment(PaymentRequest paymentRequest) throws Exception {
		LOGGER.info("Fetching invoice for ID: {}", paymentRequest.getInvoiceId());	
	    Optional<Invoice> optionalInvoice = invoiceRepo.findById(paymentRequest.getInvoiceId());
	    
	    if (!optionalInvoice.isPresent()) {
	        LOGGER.error("Invoice doesn't exist for ID: {}", paymentRequest.getInvoiceId());
	        throw new Exception("Invoice does not exist");
	    }
	   Invoice invoice = optionalInvoice.get();
	   LOGGER.info("Fetched Invoice: {}", invoice);
	   
	   Payment Addpayment=PaymentModelToEntityConverter.addPayment(paymentRequest, optionalInvoice);
	   Payment savedPayment = paymentRepo.save(Addpayment);
	    LOGGER.info("Saving Payment with ID: {}", Addpayment.getId()); 
	    
	    PaymentResponseMoodel paymentResponseMoodel=paymentEntityToModelConverter.addPayment(savedPayment);
	    return paymentResponseMoodel;
	}
	public List<PaymentResponseMoodel> getPaymentsByInvoiceId(Integer invoiceId) {
	    LOGGER.info("Fetching payments from DB for Invoice ID: {}", invoiceId);
	    
	    List<Payment> payments = paymentRepo.findByInvoiceId(invoiceId);
	    
	    if (payments.isEmpty()) {
	        LOGGER.warn("No payments found for Invoice ID: {}", invoiceId);
	    }

	    return paymentEntityToModelConverter.convertToResponseModel(payments);
	}
} 







