package com.amstech.invoice.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.converter.entity.StandardInvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.StandardInvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.repo.StandardInvoiceRepo;
import com.amstech.invoice.service.request.model.StandardInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.StandardInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.StandardInvoiceResponseModel;

@Service
public class StandardInvoiceService {
    private  final Logger LOGGER = LoggerFactory.getLogger(StandardInvoiceService.class);

	@Autowired
	private StandardInvoiceRepo standardInvoiceRepo;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private StandardInvoiceModelToEntityConverter standardInvoiceModelToEntityConverter;
	@Autowired
	private StandardInvoiceEntityToModelConverter standardInvoiceEntityToModelConverter;
	@Autowired
	private StandardInvoicePdfService pdfService;
	public StandardInvoiceResponseModel signup(StandardInvoiceSignupRequestModel requestModel) throws Exception {
	    LOGGER.info("Signup request received for Standard Invoice");

	    Optional<Company> companyOptional = companyRepo.findById(requestModel.getCompanyId());
	    if (!companyOptional.isPresent()) {
	        throw new Exception("Company does not exist");
	    }

	    Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
	    if (!clientOptional.isPresent()) {
	        throw new Exception("Client does not exist");
	    }

	    LOGGER.debug("Converting request model to entity for company: {}", requestModel.getCompanyId());

	    StandardInvoice standardInvoice = standardInvoiceModelToEntityConverter.getsaveConverter(requestModel);

	    // **Auto-generate Invoice Number**
	    standardInvoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

	    standardInvoice.setCompany(companyOptional.get());
	    standardInvoice.setClient(clientOptional.get());

	    StandardInvoice savedInvoice = standardInvoiceRepo.save(standardInvoice);

	    // **Generate PDF Path**
	    String pdfPath = pdfService.generateStandardInvoicePDF(savedInvoice);
	    savedInvoice.setPdfPath(pdfPath);
	    standardInvoiceRepo.save(savedInvoice);

	    LOGGER.info("Standard Invoice created successfully!");

	    return standardInvoiceEntityToModelConverter.getfindInvoiceById(savedInvoice);
	}


	    public StandardInvoiceResponseModel update(StandardInvoiceUpdateRequestModel requestModel) throws Exception {
	        Optional<StandardInvoice> existingInvoice = standardInvoiceRepo.findById(requestModel.getId());

	        if (existingInvoice.isEmpty()) {
	            throw new Exception("Invoice with ID " + requestModel.getId() + " does not exist");
	        }

	        StandardInvoice standardInvoice = existingInvoice.get();
	        standardInvoice = standardInvoiceModelToEntityConverter.getUpdateConverter(requestModel, standardInvoice);
	        standardInvoiceRepo.save(standardInvoice);

	        LOGGER.info("Standard Invoice Updated with ID: {}", standardInvoice.getId());
	        return standardInvoiceEntityToModelConverter.getfindInvoiceById(standardInvoice);
	    }

	    public StandardInvoiceResponseModel findInvoiceById(Integer id) throws Exception {
	        LOGGER.info("Finding invoice with ID: {}", id);
	        Optional<StandardInvoice> standardInvoice = standardInvoiceRepo.findById(id);

	        if (standardInvoice.isEmpty()) {
	            LOGGER.error("Invoice with ID {} not found", id);
	            throw new Exception("Invoice with ID " + id + " not found");
	        }

	        StandardInvoice invoice = standardInvoice.get();
	        if (invoice.getIsDeleted() == 1) {
	            throw new Exception("Invoice is deactivated.");
	        }
	        LOGGER.info("Invoice with ID {} found successfully", id);
	        return standardInvoiceEntityToModelConverter.getfindInvoiceById(invoice);
	    }

	    public List<StandardInvoiceResponseModel> findAllInvoices(Integer page, Integer size) throws Exception {
	        LOGGER.info("Fetching standard invoices for page {} with size {}", page, size);

	        Page<StandardInvoice> standardInvoicePage = standardInvoiceRepo.findAllActive(PageRequest.of(page, size));
	        LOGGER.info("Total Standard Invoices Found in DB: {}", standardInvoicePage.getTotalElements());

	        List<StandardInvoice> standardInvoices = standardInvoicePage.getContent();

	        if (standardInvoices.isEmpty()) {
	            LOGGER.warn("No standard invoices found in the database");
	            throw new Exception("No standard invoices found");
	        }

	        LOGGER.info("Successfully retrieved {} standard invoices", standardInvoices.size());
	        return standardInvoiceEntityToModelConverter.findAllInvoices(standardInvoices);
	    }

	    public long countInvoices() {
	        LOGGER.info("Counting all active standard invoices");
	        return standardInvoiceRepo.countAllInvoice();
	    }
	

    public void softDeleteById(Integer id) throws Exception {
    	LOGGER.info("Attempting to soft delete invoice with ID: {}", id);
        Optional<StandardInvoice> invoiceOptional = standardInvoiceRepo.findById(id);

        if (!invoiceOptional.isPresent()) {
        	LOGGER.error("Invoice with ID {} does not exist", id);
            throw new Exception("Invoice does not exist.");
        }

        StandardInvoice standardInvoice = invoiceOptional.get();

        if (standardInvoice.getIsDeleted() == 1) {
        	LOGGER.warn("Invoice with ID {} is already deleted", id);
            throw new Exception("Invoice already deleted.");
        }

        standardInvoice.setIsDeleted(1);
        standardInvoiceRepo.save(standardInvoice);
        LOGGER.info("Invoice with ID {} successfully soft deleted", id);
    }
    
    public void restoreById(Integer id, Integer status) throws Exception {
        Optional<StandardInvoice> invoiceOptional = standardInvoiceRepo.findById(id);

        if (invoiceOptional.isEmpty()) {
            throw new RuntimeException("Invoice does not exist.");
        }

        StandardInvoice standardInvoice = invoiceOptional.get();

        // Check if invoice is already active
        if (standardInvoice.getIsDeleted() == 0) {
            throw new Exception("Invoice is already active.");
        }

        standardInvoice.setIsDeleted(status);
        standardInvoiceRepo.save(standardInvoice);
    }

    

}
