package com.amstech.invoice.service.converter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;

@Component
public class InvoiceEntityToModelConverter {

	public List<InvoiceResponseModel> findAll(List<Invoice> invoices) {
	    List<InvoiceResponseModel> invoiceResponse = new ArrayList<>();

	    for (Invoice invoice : invoices) { 
	    	InvoiceResponseModel response = new InvoiceResponseModel();
	        
	        response.setInvoiceNumber(invoice.getInvoiceNumber());
	        invoice.getClient();
	        invoice.getPayment();
	        invoice.getInvoiceType();
	        invoice.getInvoiceItem();
	        response.setDiscount(invoice.getDiscount());
	        response.setDueDate(invoice.getDueDate());
	        response.setIssueDate(invoice.getIssueDate());
	        response.setGrandTotal(invoice.getGrandTotal());
	        response.setBalance(invoice.getBalance());
	        response.setPaid(invoice.getPaid());
	        response.setProductCode(invoice.getProductCode());
	        response.setQuantity(invoice.getQuantity());
	        response.setTax(invoice.getTax());
	        response.setTotalAmount(invoice.getTotalAmount());
	        response.setStatus(invoice.getStatus());
	        response.setId(invoice.getId());
	        invoiceResponse.add(response);
	        }
	    return invoiceResponse;
	}

    public InvoiceResponseModel getfindById(Invoice invoice) {

    	InvoiceResponseModel responseModel = new InvoiceResponseModel();

        responseModel.setId(invoice.getId());
        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
        responseModel.setIssueDate(invoice.getIssueDate());
        responseModel.setDueDate(invoice.getDueDate());
        responseModel.setTotalAmount(invoice.getTotalAmount());
        responseModel.setStatus(invoice.getStatus());
        responseModel.setSubTotal(invoice.getSubTotal());
        responseModel.setDiscount(invoice.getDiscount());
        responseModel.setTax(invoice.getTax());
        responseModel.setShipping(invoice.getShipping());
        responseModel.setGrandTotal(invoice.getGrandTotal());
        responseModel.setPaid(invoice.getPaid());
        responseModel.setBalance(invoice.getBalance());
        responseModel.setQuantity(invoice.getQuantity());
        responseModel.setProductCode(invoice.getProductCode());
        
        return responseModel;
    }
    
    public InvoiceResponseModel getfingByInvoiceNumber(Invoice invoice) {

    	InvoiceResponseModel responseModel = new InvoiceResponseModel();

        responseModel.setId(invoice.getId());
        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
        responseModel.setIssueDate(invoice.getIssueDate());
        responseModel.setDueDate(invoice.getDueDate());
        responseModel.setTotalAmount(invoice.getTotalAmount());
        responseModel.setStatus(invoice.getStatus());
        responseModel.setSubTotal(invoice.getSubTotal());
        responseModel.setDiscount(invoice.getDiscount());
        responseModel.setTax(invoice.getTax());
        responseModel.setShipping(invoice.getShipping());
        responseModel.setGrandTotal(invoice.getGrandTotal());
        responseModel.setPaid(invoice.getPaid());
        responseModel.setBalance(invoice.getBalance());
        responseModel.setQuantity(invoice.getQuantity());
        responseModel.setProductCode(invoice.getProductCode());
        
        return responseModel;
    }
//    public ClientResponseModel getfindByClientId(Invoice invoice) {
//        ClientResponseModel clientResponseModel = new ClientResponseModel();
//
//        Client client = invoice.getClient(); 
//        if (client == null) {
//            throw new IllegalArgumentException("Client information is missing for this invoice.");
//        }
//        clientResponseModel.setClientId(client.getId());
//        clientResponseModel.setClientFirstName(client.getFirstName());
//        clientResponseModel.setClientLastName(client.getLastName());
//
//        clientResponseModel.setInvoiceNumber(invoice.getInvoiceNumber());
//        clientResponseModel.setIssueDate(invoice.getIssueDate());
//        clientResponseModel.setDueDate(invoice.getDueDate());
//        clientResponseModel.setTotalAmount(invoice.getTotalAmount());
//        clientResponseModel.setStatus(invoice.getStatus());
//        clientResponseModel.setDiscount(invoice.getDiscount());
//        clientResponseModel.setTax(invoice.getTax());
//        clientResponseModel.setQuantity(invoice.getQuantity());
//        clientResponseModel.setProductCode(invoice.getProductCode());
//
//        return clientResponseModel;
//    }

    
}
