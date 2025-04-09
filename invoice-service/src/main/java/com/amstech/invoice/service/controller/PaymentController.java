package com.amstech.invoice.service.controller;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.request.model.PaymentRequest;
import com.amstech.invoice.service.response.ResponseMessage;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;
import com.amstech.invoice.service.service.InvoiceService;
import com.amstech.invoice.service.service.PaymentService;


@RestController
@RequestMapping("invoices")
public class PaymentController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    public PaymentService paymentService;
    
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
        LOGGER.info("PaymentController: Object Created");
    }
    @RequestMapping(method = RequestMethod.POST, value = "/addPayment", consumes = "application/json", produces = "application/json")    
    public ResponseMessage createPayment(@RequestBody PaymentRequest paymentRequest) {
        LOGGER.info("Received Request: {}", paymentRequest);
        try {
            PaymentResponseMoodel paymentResponseMoodel = paymentService.addPayment(paymentRequest);
            return ResponseMessage.build().withSuccess("Payment added successfully", paymentResponseMoodel);
        } catch (Exception e) {
            LOGGER.error("Failed to add payment due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }
    @RequestMapping(method=RequestMethod.GET,value="/findByInvoiceId",produces="application/json")
    public ResponseMessage findByInvoice(@RequestParam("invoiceId") Integer invoiceId) {
        LOGGER.info("Fetching payments for Invoice ID: {}", invoiceId);
        try {
            List<PaymentResponseMoodel> payments = paymentService.getPaymentsByInvoiceId(invoiceId);
            return ResponseMessage.build().withSuccess("Payments Found successfully", payments);
        }catch (Exception e) {
            LOGGER.error("Error fetching payments: {}", e.getMessage(), e);
            return ResponseMessage.build().withError("Failed to fetch payments.");
        }
        }
}

