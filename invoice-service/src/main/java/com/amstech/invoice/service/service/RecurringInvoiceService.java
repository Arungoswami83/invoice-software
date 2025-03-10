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

	public void signup(RecurringInvoiceSignupRequestModel requestModel) throws Exception {
		   Optional<Company> companyOptional = companyRepo.findById(requestModel.getCompanyId());
		   
			  if(!companyOptional.isPresent()) {
				  throw new Exception ("comapny does not exist");
			  }
			  Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
			   
			  if(!clientOptional.isPresent()) {
				  throw new Exception ("client does not exist");
			  }
	    RecurringInvoice recurringInvoice = recurringInvoiceModelToEntityConverter.getsaveConverter(requestModel);
	    recurringInvoice.setCompany(companyOptional.get()); 
	    recurringInvoice.setClient(clientOptional.get());   
	    recurringInvoiceRepo.save(recurringInvoice);
	}

	    public void update(RecurringInvoiceUpdateRequestModel recurringInvoiceUpdateRequestModel) {
	        LOGGER.info("Attempting to update recurring invoice with ID: {}", recurringInvoiceUpdateRequestModel.getId());

	        Optional<RecurringInvoice> existingInvoice = recurringInvoiceRepo.findById(recurringInvoiceUpdateRequestModel.getId());

	        if (existingInvoice.isEmpty()) {
	            LOGGER.error("Update failed: Invoice with ID {} does not exist", recurringInvoiceUpdateRequestModel.getId());
	            throw new RuntimeException("Invoice with ID " + recurringInvoiceUpdateRequestModel.getId() + " does not exist");
	        }

	        RecurringInvoice recurringInvoice = existingInvoice.get();
	        recurringInvoice = recurringInvoiceModelToEntityConverter.getUpdateConverter(recurringInvoiceUpdateRequestModel, recurringInvoice);
	        recurringInvoiceRepo.save(recurringInvoice);

	        LOGGER.info("Recurring invoice with ID {} updated successfully", recurringInvoiceUpdateRequestModel.getId());
	    }

	    public RecurringInvoiceResponseModel findById(Integer id) throws Exception {
	        LOGGER.info("Fetching recurring invoice by ID: {}", id);

	        Optional<RecurringInvoice> existingInvoice = recurringInvoiceRepo.findById(id);

	        if (existingInvoice.isEmpty()) {
	            LOGGER.error("Fetch failed: Invoice with ID {} does not exist", id);
	            throw new RuntimeException("Invoice with ID " + id + " does not exist");
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
	            // Fetching invoices with pagination
	            Page<RecurringInvoice> recurringInvoicePage = recurringInvoiceRepo.findAllActive(PageRequest.of(page, size));
	            List<RecurringInvoice> recurringInvoices = recurringInvoicePage.getContent();

	            if (recurringInvoices.isEmpty()) {
	                LOGGER.warn("No recurring invoices found on page {} with size {}", page, size);
	                throw new Exception("No recurring invoices found");
	            }

	            LOGGER.info("Successfully retrieved {} recurring invoices on page {}", recurringInvoices.size(), page);

	            // Converting the entity list to response models and returning
	            return recurringInvoiceEntityToModelConverter.getfindAllConverter(recurringInvoices);
	        } catch (Exception e) {
	            // Logging the error and re-throwing for further handling
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
	    }	}


