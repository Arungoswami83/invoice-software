package com.amstech.invoice.service.service;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.converter.entity.RecurringInvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.RecurringInvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.repo.RecurringInvoiceRepo;
import com.amstech.invoice.service.repo.ServiceInvoiceRepo;
import com.amstech.invoice.service.request.model.RecurringInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.RecurringInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.RecurringInvoiceResponseModel;

@Service
public class RecurringInvoiceService {
    private  final Logger LOGGER = LoggerFactory.getLogger(RecurringInvoiceService.class);

	@Autowired
	private RecurringInvoiceRepo recurringInvoiceRepo;
	@Autowired
	private RecurringInvoiceModelToEntityConverter recurringInvoiceModelToEntityConverter;
	@Autowired
	private RecurringInvoiceEntityToModelConverter recurringInvoiceEntityToModelConverter;
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private CompanyRepo companyRepo;

	 public RecurringInvoiceResponseModel signup(RecurringInvoiceSignupRequestModel requestModel) throws Exception {
	        Optional<Company> companyOptional = companyRepo.findById(requestModel.getCompanyId());
	        if (!companyOptional.isPresent()) {
	            throw new Exception("Company does not exist");
	        }

	        Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
	        if (!clientOptional.isPresent()) {
	            throw new Exception("Client does not exist");
	        }

	        RecurringInvoice recurringInvoice = recurringInvoiceModelToEntityConverter.getsaveConverter(requestModel);
	        recurringInvoice.setCompany(companyOptional.get());
	        recurringInvoice.setClient(clientOptional.get());

	        recurringInvoice = recurringInvoiceRepo.save(recurringInvoice);
	        LOGGER.info("Recurring Invoice created successfully!");

	        return recurringInvoiceEntityToModelConverter.getFindByIdConverter(recurringInvoice);
	    }

	    public RecurringInvoiceResponseModel update(RecurringInvoiceUpdateRequestModel requestModel) {
	        LOGGER.info("Attempting to update recurring invoice with ID: {}", requestModel.getId());

	        Optional<RecurringInvoice> existingInvoice = recurringInvoiceRepo.findById(requestModel.getId());
	        if (existingInvoice.isEmpty()) {
	            LOGGER.error("Update failed: Invoice with ID {} does not exist", requestModel.getId());
	            throw new RuntimeException("Invoice with ID " + requestModel.getId() + " does not exist");
	        }

	        RecurringInvoice recurringInvoice = existingInvoice.get();
	        recurringInvoice = recurringInvoiceModelToEntityConverter.getUpdateConverter(requestModel, recurringInvoice);
	        recurringInvoice = recurringInvoiceRepo.save(recurringInvoice);

	        LOGGER.info("Recurring invoice with ID {} updated successfully", requestModel.getId());
	        return recurringInvoiceEntityToModelConverter.getFindByIdConverter(recurringInvoice);
	    }

	    public RecurringInvoiceResponseModel findById(Integer id) throws Exception {
	        LOGGER.info("Fetching recurring invoice by ID: {}", id);

	        Optional<RecurringInvoice> existingInvoice = recurringInvoiceRepo.findById(id);
	        if (existingInvoice.isEmpty()) {
	            LOGGER.error("Fetch failed: Invoice with ID {} does not exist", id);
	            throw new Exception("Invoice with ID " + id + " does not exist");
	        }

	        RecurringInvoice invoice = existingInvoice.get();
	        if (invoice.getIsDeleted() == 1) {
	            throw new Exception("Invoice is deactivated.");
	        }

	        LOGGER.info("Recurring invoice with ID {} retrieved successfully", id);
	        return recurringInvoiceEntityToModelConverter.getFindByIdConverter(invoice);
	    }

	    public List<RecurringInvoiceResponseModel> findAllRecurringInvoices(Integer page, Integer size) throws Exception {
	        LOGGER.info("Fetching recurring invoices for page {} with size {}", page, size);

	        try {
	            Page<RecurringInvoice> recurringInvoicePage = recurringInvoiceRepo.findAllActive(PageRequest.of(page, size));
	            List<RecurringInvoice> recurringInvoices = recurringInvoicePage.getContent();

	            if (recurringInvoices.isEmpty()) {
	                LOGGER.warn("No recurring invoices found on page {} with size {}", page, size);
	                throw new Exception("No recurring invoices found");
	            }

	            LOGGER.info("Successfully retrieved {} recurring invoices on page {}", recurringInvoices.size(), page);
	            return recurringInvoiceEntityToModelConverter.getfindAllConverter(recurringInvoices);
	        } catch (Exception e) {
	            LOGGER.error("Error while fetching recurring invoices: {}", e.getMessage(), e);
	            throw new Exception("Error while fetching recurring invoices", e);
	        }
	    }

	    public long countRecurringInvoices() {
	        LOGGER.info("Counting all active recurring invoices");
	        return recurringInvoiceRepo.countActiveInvoices();
	    }
	
	   
	    public void softDeleteById(Integer id) throws Exception {
	        LOGGER.info("Attempting to soft delete recurring invoice with ID: {}", id);

	        Optional<RecurringInvoice> invoiceOptional = recurringInvoiceRepo.findById(id);

	        if (!invoiceOptional.isPresent()) {
	            LOGGER.error("Soft delete failed: Invoice with ID {} does not exist", id);
	            throw new Exception("Invoice does not exist.");
	        }

	        RecurringInvoice recurringInvoice = invoiceOptional.get();
	        if (recurringInvoice.getIsDeleted() == 1) {
	            LOGGER.warn("Soft delete ignored: Invoice with ID {} is already deleted", id);
	            throw new Exception("Invoice already deleted.");
	        }

	        recurringInvoice.setIsDeleted(1);
	        recurringInvoiceRepo.save(recurringInvoice);

	        LOGGER.info("Recurring invoice with ID {} soft deleted successfully", id);
	    }
	    
	    public void restoreById(Integer id, Integer status) throws Exception {
	        Optional<RecurringInvoice> invoiceOptional = recurringInvoiceRepo.findById(id);

	        if (invoiceOptional.isEmpty()) {
	            throw new RuntimeException("Invoice does not exist.");
	        }

	        RecurringInvoice recurringInvoice = invoiceOptional.get();

	        // Check if invoice is already active
	        if (recurringInvoice.getIsDeleted() == 0) {
	            throw new Exception("Invoice is already active.");
	        }

	        recurringInvoice.setIsDeleted(status);
	        recurringInvoiceRepo.save(recurringInvoice);
	    }

	    }


