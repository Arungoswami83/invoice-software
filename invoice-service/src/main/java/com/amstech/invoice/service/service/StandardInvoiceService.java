package com.amstech.invoice.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	public void signup(StandardInvoiceSignupRequestModel requestModel) throws Exception {
	    Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
	    if (clientOptional.isEmpty()) {
	        throw new RuntimeException("Client does not exist");
	    }

	    Company company = companyRepo.findById(requestModel.getCompanyId())
	        .orElseThrow(() -> new RuntimeException("Company not found"));

	    // ✅ Converter ko pehle call karein
	    StandardInvoice standardInvoice = standardInvoiceModelToEntityConverter.getsaveConverter(requestModel);

	    // ✅ Ab company set karein
	    standardInvoice.setCompany(company);

	    // ✅ Now save
	    standardInvoiceRepo.save(standardInvoice);
	    LOGGER.info("Standard Invoice Saved with ID:{} " + standardInvoice.getId());
	}

	public void update(StandardInvoiceUpdateRequestModel requestModel) throws Exception {
	    Optional<StandardInvoice> existingInvoice = standardInvoiceRepo.findById(requestModel.getId());

	    if (existingInvoice.isEmpty()) {
	        throw new Exception("Invoice with ID " + requestModel.getId() + " does not exist");
	    }

	    StandardInvoice standardInvoice = existingInvoice.get();
       
	    standardInvoice = standardInvoiceModelToEntityConverter.getUpdateConverter(requestModel, standardInvoice);
	    standardInvoiceRepo.save(standardInvoice);
	    LOGGER.info("Standard Invoice Updated with ID: " + standardInvoice.getId());
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
			throw new Exception("invoice is desctivate.");
		}
        LOGGER.info("Invoice with ID {} found successfully", id);
        return standardInvoiceEntityToModelConverter.findInvoiceById(invoice);
    }
	
	public List<StandardInvoiceResponseModel> findAllInvoices(Integer page, Integer size) throws Exception {
	    LOGGER.info("Fetching invoices for page {} with size {}", page, size);

	    Page<StandardInvoice> standardInvoicePage = standardInvoiceRepo.findAllActive(PageRequest.of(page, size));
	    
	    LOGGER.info("Total Invoices Found in DB: {}", standardInvoicePage.getTotalElements());

	    List<StandardInvoice> standardInvoices = standardInvoicePage.getContent();
	    
	    for (StandardInvoice invoice : standardInvoices) {
	        LOGGER.info("Invoice ID: {}, isDeleted: {}", invoice.getId(), invoice.getIsDeleted());
	    }

	    if (standardInvoices.isEmpty()) {
	        LOGGER.warn("No invoices found in the database");
	        throw new Exception("No invoices found");
	    }

	    LOGGER.info("Successfully retrieved {} invoices", standardInvoices.size());
	    return standardInvoiceEntityToModelConverter.findAllInvoices(standardInvoices);
	}




	 
    public void softDeleteById(Integer id, Integer status) throws Exception {
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

        standardInvoice.setIsDeleted(status);
        standardInvoiceRepo.save(standardInvoice);
        LOGGER.info("Invoice with ID {} successfully soft deleted", id);
    }
    

}
