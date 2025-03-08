package com.amstech.invoice.service.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.SalesInvoices;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.SalesRepo;
import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.SalesSignupRequestModel;
import com.amstech.invoice.service.request.model.SalesUpdateRequestModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;
import com.amstech.invoice.service.response.model.SalesInvoiceResponseModel;


@Service
public class SalesService {
	
	 private static final Logger logger = LoggerFactory.getLogger(SalesService.class);
	@Autowired
	private SalesRepo salesRepo;
	@Autowired
	private ClientRepo clientRepo;


	  public void signup(SalesSignupRequestModel salesSignupRequestModel) throws Exception {
	        logger.info("Starting signup process for client ID: {}", salesSignupRequestModel.getClientId());
	        
	        Optional<Client> existingClientById = clientRepo.findById(salesSignupRequestModel.getClientId());
	        if (!existingClientById.isPresent()) {
	            logger.error("Client already exists");
	            throw new Exception("Client already exists");
	        }
	        
	        SalesInvoices salesInvoices = new SalesInvoices();
	        salesInvoices.setInvoiceNumber(salesSignupRequestModel.getInvoiceNumber());
	        salesInvoices.setClientId(salesSignupRequestModel.getClientId());
	        salesInvoices.setDiscount(salesSignupRequestModel.getDiscount());
	        salesInvoices.setPaymentTerm(salesSignupRequestModel.getPaymentTerm());
	        salesInvoices.setSignature(salesSignupRequestModel.getSignature());
	        salesInvoices.setStatus(salesSignupRequestModel.getStatus());
	        salesInvoices.setSubtotal(salesSignupRequestModel.getSubtotal());
	        salesInvoices.setTax(salesSignupRequestModel.getTax());
	        salesInvoices.setTotal(salesSignupRequestModel.getTotal());
	        salesInvoices.setDate(salesSignupRequestModel.getDate());
	        salesInvoices.setDueDate(salesSignupRequestModel.getDueDate());
	        
	        SalesInvoices salesSave = salesRepo.save(salesInvoices);
	        logger.info("SalesInvoice created with ID: {}", salesSave.getId());
	    }

	    public void updateSales(SalesUpdateRequestModel salesUpdateRequestModel) throws Exception {
	        logger.info("Updating Sales Invoice with ID: {}", salesUpdateRequestModel.getId());
	        
	        Optional<SalesInvoices> optionalSalesInvoices = salesRepo.findById(salesUpdateRequestModel.getId());
	        if (!optionalSalesInvoices.isPresent()) {
	            logger.error("Sales Invoice does not exist");
	            throw new Exception("Sales Invoice does not exist");
	        }

	        SalesInvoices salesInvoices = optionalSalesInvoices.get();
	        Client client = clientRepo.findById(salesUpdateRequestModel.getClientId())
	                .orElseThrow(() -> new Exception("Client does not exist"));

	        salesInvoices.setPaymentTerm(salesUpdateRequestModel.getPaymentTerm());
	        salesInvoices.setSignature(salesUpdateRequestModel.getSignature());
	        salesInvoices.setStatus(salesUpdateRequestModel.getStatus());
	        salesInvoices.setSubtotal(salesUpdateRequestModel.getSubtotal());
	        salesInvoices.setTotal(salesUpdateRequestModel.getTotal());
	        salesInvoices.setTax(salesUpdateRequestModel.getTax());
	        salesInvoices.setClientId(client.getId());
	        
	        salesRepo.save(salesInvoices);
	        logger.info("Successfully updated Sales Invoice with ID: {}", salesInvoices.getId());
	    }

	    public SalesInvoiceResponseModel findBySalesInvoiceId(Integer id) {
	        logger.info("Fetching Sales Invoice with ID: {}", id);
	        try {
	            SalesInvoices sales = salesRepo.findById(id)
	                    .orElseThrow(() -> new RuntimeException("Sales Invoice not found with ID: " + id));

	            SalesInvoiceResponseModel responseModel = new SalesInvoiceResponseModel();
	            responseModel.setId(sales.getId());
	            
	            if (sales.getClientId() != 1) {
	                responseModel.setClientId(sales.getClientId());
	            }

	            responseModel.setDiscount(sales.getDiscount());
	            responseModel.setPaymentTerm(sales.getPaymentTerm());
	            responseModel.setSignature(sales.getSignature());
	            responseModel.setStatusName(sales.getStatus());
	            responseModel.setSubtotal(sales.getSubtotal());
	            responseModel.setTax(sales.getTax());
	            responseModel.setTotal(sales.getTotal());
	            
	            logger.info("Sales Invoice retrieved successfully!");
	            return responseModel;
	        } catch (Exception e) {
	            logger.error("Failed to retrieve Sales Invoice: {}", e.getMessage(), e);
	            throw new RuntimeException("Failed to retrieve Sales Invoice.");
	        }
	    }

	    public void softDeleteById(Integer id) throws Exception {
	        logger.info("Soft deleting Sales Invoice with ID: {}", id);
	        Optional<SalesInvoices> invoiceOptional = salesRepo.findById(id);

	        if (!invoiceOptional.isPresent()) {
	            logger.error("Sales Invoice does not exist.");
	            throw new Exception("Sales Invoice does not exist.");
	        }

	        SalesInvoices invoice = invoiceOptional.get();
	        if (invoice.getIsDeleted() == 1) {
	            logger.warn("Sales Invoice already deleted.");
	            throw new Exception("Sales Invoice already deleted.");
	        }

	        invoice.setIsDeleted(1);
	        salesRepo.save(invoice);
	        logger.info("Successfully soft deleted Sales Invoice with ID: {}", id);
	    }

	    public List<SalesInvoiceResponseModel> findAllActive(Integer page , Integer size) {
	        logger.info("Fetching all active Sales Invoices");
	        List<SalesInvoices> salesInvoicesList = salesRepo.findAll();
	        

	        
	        List<SalesInvoiceResponseModel> salesInvoiceResponseModels = new ArrayList<SalesInvoiceResponseModel>();
	        for (SalesInvoices salesInvoices : salesInvoicesList) {
				SalesInvoiceResponseModel salesInvoiceResponseModel = new SalesInvoiceResponseModel();
				salesInvoiceResponseModel.setClientId(salesInvoices.getClientId());
				salesInvoiceResponseModel.setDiscount(salesInvoices.getDiscount());
				salesInvoiceResponseModel.setId(salesInvoices.getId());
				salesInvoiceResponseModel.setPaymentTerm(salesInvoices.getPaymentTerm());
				salesInvoiceResponseModel.setSignature(salesInvoices.getSignature());
				salesInvoiceResponseModel.setStatusName(salesInvoices.getStatus());
				salesInvoiceResponseModel.setSubtotal(salesInvoices.getSubtotal());
				salesInvoiceResponseModel.setTax(salesInvoices.getTax());
				salesInvoiceResponseModel.setTotal(salesInvoices.getTotal());
				
				salesInvoiceResponseModels.add(salesInvoiceResponseModel);
			}
	        logger.info("Total active SalesInvoices fetched: {}", salesInvoiceResponseModels.size());
	        return salesInvoiceResponseModels;
	        
	    }
        public long count() throws Exception{
    	return salesRepo.count();
    }
	}
