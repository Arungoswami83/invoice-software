//package com.amstech.invoice.service.converter.entity;
//
//import java.sql.Timestamp;
//import java.util.Optional;
//
//import org.springframework.stereotype.Component;
//
//import com.amstech.invoice.service.entity.Invoice;
//import com.amstech.invoice.service.request.model.generateInvoiceRequest;
//
//@Component
//public class GenerateInvoioceConverter {
//	
//	public static Invoice GenerateConverter(generateInvoiceRequest generateInvoiceRequest,Optional<Invoice> existingInvoice) {
//		
//		Invoice invoice=new Invoice();
//		
//        existingInvoice.ifPresent(existing -> invoice.setClient(existing.getClient()));
//	
//		return invoice;
//	}
//	public static Invoice UpdateConerter(generateInvoiceRequest generateInvoiceRequest,Invoice existingInvoice) {
//		existingInvoice.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//
//        return existingInvoice;
//
//
//	}
//}