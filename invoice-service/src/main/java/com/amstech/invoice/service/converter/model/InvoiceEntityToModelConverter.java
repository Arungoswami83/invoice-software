package com.amstech.invoice.service.converter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
//	        response.setId(invoice.getId());
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

            clientResponseModel.setInvoiceId(invoice.getId()); 
            clientResponseModel.setClientId(client.getId());
            clientResponseModel.setFirstName(client.getFirstName());
            clientResponseModel.setLastName(client.getLastName());
            clientResponseModel.setInvoiceNumber(invoice.getInvoiceNumber());
            clientResponseModel.setIssueDate(invoice.getIssueDate());
            clientResponseModel.setDueDate(invoice.getDueDate());
            clientResponseModel.setTotalAmount(invoice.getTotalAmount());
            clientResponseModel.setProductCode(invoice.getProductCode());
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
        responseModel.setIssueDate(invoice.getIssueDate());
        responseModel.setDueDate(invoice.getDueDate());
        responseModel.setTotalAmount(invoice.getTotalAmount());
        responseModel.setSubTotal(invoice.getSubTotal());
        responseModel.setDiscount(invoice.getDiscount());
        responseModel.setTax(invoice.getTax());
        responseModel.setShipping(invoice.getShipping());
        responseModel.setGrandTotal(invoice.getGrandTotal());
        responseModel.setPaid(invoice.getPaid());
        responseModel.setBalance(invoice.getBalance());
        responseModel.setQuantity(invoice.getQuantity());
        responseModel.setProductCode(invoice.getProductCode());
        responseModel.setPaymentMethod(invoice.getPayment().getPaymentMethod().name());
        responseModel.setPdfUrl(invoice.getPdfPath());

        return responseModel;
    }
}
      
