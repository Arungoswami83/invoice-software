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
import com.amstech.invoice.service.entity.ProductInvoice;
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

	 public ProformaInvoiceResponseModel signup(ProformaInvoiceSignupRequestModel requestModel) throws Exception {
		  Optional<Company> companyOptional = companyRepo.findById(requestModel.getCompanyId());
	        if (!companyOptional.isPresent()) {
	            throw new Exception("Company does not exist");
	        }

	        LOGGER.debug("Converting request model to entity for user: {}", requestModel.getCompanyId());

	        Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
	        if (!clientOptional.isPresent()) {
	            throw new Exception("Client does not exist");
	        }

	        ProformaInvoice proformaInvoice = proformaInvoiceModelToEntityConverter.getSignupConverter(requestModel);
	        proformaInvoice.setCompany(companyOptional.get());
	        proformaInvoice.setClient(clientOptional.get());
	        ProformaInvoice savedInvoice = proformaInvoiceRepo.save(proformaInvoice);

	        return proformaInvoiceEntityToModelConverter.getfindbyid(savedInvoice);
	    }

	 
	 
	    public ProformaInvoiceResponseModel update(ProformaInvoiceUpdateRequestModel requestModel) throws Exception {
	        Optional<ProformaInvoice> invoiceOptional = proformaInvoiceRepo.findById(requestModel.getId());

	        if (invoiceOptional.isEmpty()) {
	            throw new Exception("Invoice does not exist.");
	        }

	        ProformaInvoice proformaInvoice = invoiceOptional.get();
	        LOGGER.info("Updating invoice with ID: {}", requestModel.getId());

	        proformaInvoice = proformaInvoiceModelToEntityConverter.getUpdateConverter(requestModel, proformaInvoice);
	        proformaInvoiceRepo.save(proformaInvoice);

	        LOGGER.info("Invoice with ID {} updated successfully.", requestModel.getId());
	        return proformaInvoiceEntityToModelConverter.getfindbyid(proformaInvoice);
	    }

	    public ProformaInvoiceResponseModel findInvoiceById(Integer id) throws Exception {
	        LOGGER.info("Fetching invoice by ID: {}", id);

	        Optional<ProformaInvoice> invoiceOptional = proformaInvoiceRepo.findById(id);

	        if (!invoiceOptional.isPresent()) {
	            LOGGER.error("Invoice with ID {} does not exist.", id);
	            throw new Exception("Invoice does not exist.");
	        }

	        ProformaInvoice proformaInvoice = invoiceOptional.get();
	        if (proformaInvoice.getIsDeleted() == 1) {
	            LOGGER.warn("Invoice with ID {} is already deleted.", id);
	            throw new Exception("Invoice already deleted.");
	        }

	        LOGGER.info("Invoice with ID {} fetched successfully.", id);
	        return proformaInvoiceEntityToModelConverter.getfindbyid(proformaInvoice);
	    }

	    public List<ProformaInvoiceResponseModel> findAll(Integer page, Integer size) throws Exception {
	        LOGGER.info("Fetching all invoices...");

	        Page<ProformaInvoice> proformaInvoicePage = proformaInvoiceRepo.findAllActive(PageRequest.of(page, size));
	        List<ProformaInvoice> proformaInvoiceList = proformaInvoicePage.getContent();

	        if (proformaInvoiceList.isEmpty()) {
	            throw new Exception("No invoices found.");
	        }

	        LOGGER.info("Fetched {} invoices successfully", proformaInvoiceList.size());
	        return proformaInvoiceEntityToModelConverter.getfindAllConverter(proformaInvoiceList);
	    }

	    public long countAllInvoices() throws Exception {
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
	
	  public void restoreById(Integer id, Integer status) throws Exception {
	        Optional<ProformaInvoice> invoiceOptional = proformaInvoiceRepo.findById(id);

	        if (invoiceOptional.isEmpty()) {
	            throw new RuntimeException("Invoice does not exist.");
	        }

	        ProformaInvoice proformaInvoice = invoiceOptional.get();

	        // Check if invoice is already active
	        if (proformaInvoice.getIsDeleted() == 0) {
	            throw new Exception("Invoice is already active.");
	        }

	        proformaInvoice.setIsDeleted(status);
	        proformaInvoiceRepo.save(proformaInvoice);
	    }

}
