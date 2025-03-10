package com.amstech.invoice.service.service;

import java.sql.Date;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.converter.entity.ProformaInvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.ProformaInvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.repo.ProformaInvoiceRepo;
import com.amstech.invoice.service.request.model.ProformaInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProformaInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProformaInvoiceService {
    private  final Logger LOGGER = LoggerFactory.getLogger(ProformaInvoiceService.class);

	@Autowired
	private ProformaInvoiceRepo proformaInvoiceRepo;
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private ProformaInvoiceModelToEntityConverter proformaInvoiceModelToEntityConverter;
	@Autowired
	private ProformaInvoiceEntityToModelConverter proformaInvoiceEntityToModelConverter;

	

	public void Signup(ProformaInvoiceSignupRequestModel requestModel) throws Exception {
	   Optional<Company> companyOptional = companyRepo.findById(requestModel.getCompanyId());
	   
	  if(!companyOptional.isPresent()) {
		  throw new Exception ("comapny does not exist");
	  }
	  Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
	   
	  if(!clientOptional.isPresent()) {
		  throw new Exception ("client does not exist");
	  }
	    ProformaInvoice proformaInvoice = new ProformaInvoice();

	    ProformaInvoice invoice = proformaInvoiceModelToEntityConverter.getSignupConverter(requestModel);
	    invoice.setCompany(companyOptional.get()); // सही तरीके से सेट करें
	    invoice.setClient(clientOptional.get());   // सही तरीके से सेट करें
	    proformaInvoiceRepo.save(invoice);
	}


	public void update(ProformaInvoiceUpdateRequestModel requestModel) throws Exception {
	    LOGGER.info("Updating proforma invoice with ID: {}", requestModel.getId());

	    ProformaInvoice proformaInvoice = proformaInvoiceRepo.findById(requestModel.getId()).orElse(null);
	    if (proformaInvoice == null) {
	        LOGGER.error("Invoice with ID {} does not exist", requestModel.getId());
	        throw new Exception("Invoice with ID " + requestModel.getId() + " does not exist");
	    }

	    proformaInvoice = proformaInvoiceModelToEntityConverter.getUpdateConverter(requestModel, proformaInvoice);
	    proformaInvoiceRepo.save(proformaInvoice);
	    
	    LOGGER.info("Proforma invoice with ID {} updated successfully", requestModel.getId());
	}

	public ProformaInvoiceResponseModel findById(Integer id) throws Exception {
	    LOGGER.info("Fetching proforma invoice with ID: {}", id);

	    ProformaInvoice proformaInvoice = proformaInvoiceRepo.findById(id).orElse(null);
	    if (proformaInvoice == null) {
	        LOGGER.error("Invoice with ID {} not found", id);
	        throw new Exception("Invoice with ID " + id + " not found");
	    }

	    if (proformaInvoice.getIsDeleted() == 1) {
	        throw new Exception("Invoice is deactivated.");
	    }

	    LOGGER.info("Proforma invoice with ID {} fetched successfully", id);
	    return proformaInvoiceEntityToModelConverter.getfindbyid(proformaInvoice);
	}

	public List<ProformaInvoiceResponseModel> findAllInvoices(Integer page, Integer size) throws Exception {
	    LOGGER.info("Fetching proforma invoices for page {} with size {}", page, size);

	    Page<ProformaInvoice> proformaInvoicePage = proformaInvoiceRepo.findAllActive(PageRequest.of(page, size));
	    List<ProformaInvoice> proformaInvoices = proformaInvoicePage.getContent();  

	    if (proformaInvoices.isEmpty()) {
	        LOGGER.warn("No invoices found in the system.");
	        throw new Exception("No invoices found");
	    }

	    LOGGER.info("Fetched {} proforma invoices", proformaInvoices.size());
	    return proformaInvoiceEntityToModelConverter.getfindAllConverter(proformaInvoices);
	}

	public long count() {
	    return proformaInvoiceRepo.countActiveInvoices();  
	}

	


	public void softDeleteById(Integer id) throws Exception {
	    LOGGER.info("Soft deleting proforma invoice with ID: {}", id);

	    ProformaInvoice proformaInvoice = proformaInvoiceRepo.findById(id).orElse(null);
	    if (proformaInvoice == null) {
	        LOGGER.error("Invoice with ID {} does not exist", id);
	        throw new Exception("Invoice does not exist.");
	    }

	    if (proformaInvoice.getIsDeleted() == 1) {
	        LOGGER.warn("Invoice with ID {} is already deleted", id);
	        throw new Exception("Invoice already deleted.");
	    }

	    proformaInvoice.setIsDeleted(1);
	    proformaInvoiceRepo.save(proformaInvoice);

	    LOGGER.info("Proforma invoice with ID {} soft deleted successfully", id);
	}

}
