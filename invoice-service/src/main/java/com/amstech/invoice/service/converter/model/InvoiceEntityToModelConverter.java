package com.amstech.invoice.service.converter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;

@Component
public class InvoiceEntityToModelConverter {

	public List<InvoiceResponseModel> findAll(List<Invoice> invoices) {
	    List<InvoiceResponseModel> invoiceResponse = new ArrayList<>();

	    for (Invoice invoice : invoices) { 
	    	InvoiceResponseModel response = new InvoiceResponseModel();
	        
	        response.setInvoiceNumber(invoice.getInvoiceNumber());
	        invoice.getClient();
	        response.setDiscount(invoice.getDiscount());
	        response.setDueDate(invoice.getDueDate());
	        response.setCreatedAt(invoice.getCreatedAt());
	        response.setQuantity(invoice.getQuantity());
	        response.setTax(invoice.getTax());
	        response.setTotalAmount(invoice.getTotalAmount());
	        invoiceResponse.add(response);
	        }
	    return invoiceResponse;
	}

    public InvoiceResponseModel getfindById(Invoice invoice) {

    	InvoiceResponseModel responseModel = new InvoiceResponseModel();

        responseModel.setId(invoice.getId());
        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
        responseModel.setCreatedAt(invoice.getCreatedAt());
        responseModel.setDueDate(invoice.getDueDate());
        responseModel.setTotalAmount(invoice.getTotalAmount());
        responseModel.setSubTotal(invoice.getSubTotal());
        responseModel.setDiscount(invoice.getDiscount());
        responseModel.setTax(invoice.getTax());
        responseModel.setQuantity(invoice.getQuantity());
        
        return responseModel;
    }
    
    public InvoiceResponseModel getfingByInvoiceNumber(Invoice invoice) {

    	InvoiceResponseModel responseModel = new InvoiceResponseModel();

        responseModel.setId(invoice.getId());
        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
        responseModel.setCreatedAt(invoice.getCreatedAt());
        responseModel.setDueDate(invoice.getDueDate());
        responseModel.setTotalAmount(invoice.getTotalAmount());
        responseModel.setSubTotal(invoice.getSubTotal());
        responseModel.setDiscount(invoice.getDiscount());
        responseModel.setTax(invoice.getTax());
        responseModel.setQuantity(invoice.getQuantity());
        
        return responseModel;
    }
    public List<ClientResponseModel> mapInvoiceToClientResponse(List<Invoice> invoices) throws Exception {
        if (invoices == null || invoices.isEmpty()) {
            throw new IllegalArgumentException("Invoice list is empty or null.");
        }

        List<ClientResponseModel> responseList = new ArrayList<>();

        for (Invoice invoice : invoices) {
            if (invoice.getClient() == null) {
                throw new Exception("Client information is missing for invoice: " + invoice.getInvoiceNumber());
            }

            Client client = invoice.getClient();
            ClientResponseModel clientResponseModel = new ClientResponseModel();

            clientResponseModel.setClientId(client.getId());
            clientResponseModel.setFirstName(client.getFirstName());
            clientResponseModel.setLastName(client.getLastName());
            clientResponseModel.setCreatedAt(invoice.getCreatedAt());
            clientResponseModel.setDueDate(invoice.getDueDate());
            clientResponseModel.setTotalAmount(invoice.getTotalAmount());
            clientResponseModel.setQuantity(invoice.getQuantity());
            clientResponseModel.setTax(invoice.getTax());

            responseList.add(clientResponseModel);
        }

        return responseList;
    }
    public InvoiceResponseModel convertEntityToModel(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        InvoiceResponseModel responseModel = new InvoiceResponseModel();
        responseModel.setId(invoice.getId()); 
        responseModel.setInvoiceNumber(invoice.getInvoiceNumber());
        responseModel.setCreatedAt(invoice.getCreatedAt());
        responseModel.setDueDate(invoice.getDueDate());
        responseModel.setTotalAmount(invoice.getTotalAmount());
        responseModel.setSubTotal(invoice.getSubTotal());
        responseModel.setDiscount(invoice.getDiscount());
        responseModel.setTax(invoice.getTax());
        responseModel.setQuantity(invoice.getQuantity());
        responseModel.setPdfUrl(invoice.getPdfPath());
        responseModel.setBalance(invoice.getBalance());
        responseModel.setPaid(invoice.getPaid());
        responseModel.setCategory(invoice.getCategory().name());
        responseModel.setProductCode(invoice.getProductCode());
        
        return responseModel;
    }
    
    
}
      
