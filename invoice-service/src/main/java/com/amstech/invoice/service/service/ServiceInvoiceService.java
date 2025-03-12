package com.amstech.invoice.service.service;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.converter.entity.ServiceInvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.ServiceInvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.ServiceInvoiceRepo;
import com.amstech.invoice.service.request.model.ServiceInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ServiceInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.ServiceInvoiceResponseModel;



@Service
public class ServiceInvoiceService {
    private  final Logger LOGGER = LoggerFactory.getLogger(ServiceInvoiceService.class);


	@Autowired
	private ServiceInvoiceRepo serviceInvoiceRepo;
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private ServiceInvoiceModelToEntityConverter serviceInvoiceModelToEntityConverter;
	@Autowired
	private ServiceInvoiceEntityToModelConverter serviceInvoiceEntityToModelConverter;

	 public ServiceInvoiceResponseModel signup(ServiceInvoiceSignupRequestModel requestModel) throws Exception {
	        LOGGER.info("Signup request received for Service Invoice");

	        Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
	        if (!clientOptional.isPresent()) {
	            throw new Exception("Client does not exist");
	        }

	        ServiceInvoice serviceInvoice = serviceInvoiceModelToEntityConverter.getsaveConverter(requestModel);
	        serviceInvoice.setClient(clientOptional.get());

	        serviceInvoice = serviceInvoiceRepo.save(serviceInvoice);
	        LOGGER.info("Service Invoice created successfully!");

	        return serviceInvoiceEntityToModelConverter.getFindByIdConverter(serviceInvoice);
	    }

	    // ✅ Update Service Invoice
	    public ServiceInvoiceResponseModel update(ServiceInvoiceUpdateRequestModel requestModel) throws Exception {
	        LOGGER.info("Attempting to update service invoice with ID: {}", requestModel.getId());

	        Optional<ServiceInvoice> existingInvoice = serviceInvoiceRepo.findById(requestModel.getId());
	        if (existingInvoice.isEmpty()) {
	            LOGGER.error("Update failed: Invoice with ID {} does not exist", requestModel.getId());
	            throw new Exception("Invoice with ID " + requestModel.getId() + " does not exist");
	        }

	        ServiceInvoice serviceInvoice = existingInvoice.get();
	        serviceInvoice = serviceInvoiceModelToEntityConverter.getUpdateConverter(requestModel, serviceInvoice);
	        serviceInvoice = serviceInvoiceRepo.save(serviceInvoice);

	        LOGGER.info("Service invoice with ID {} updated successfully", requestModel.getId());
	        return serviceInvoiceEntityToModelConverter.getFindByIdConverter(serviceInvoice);
	    }

	    // ✅ Find Service Invoice by ID
	    public ServiceInvoiceResponseModel findById(Integer id) throws Exception {
	        LOGGER.info("Fetching service invoice by ID: {}", id);

	        Optional<ServiceInvoice> existingInvoice = serviceInvoiceRepo.findById(id);
	        if (existingInvoice.isEmpty()) {
	            LOGGER.error("Fetch failed: Invoice with ID {} does not exist", id);
	            throw new Exception("Invoice with ID " + id + " does not exist");
	        }

	        ServiceInvoice invoice = existingInvoice.get();
	        if (invoice.getIsDeleted() == 1) {
	            LOGGER.error("Service Invoice with ID {} is deactivated", id);
	            throw new Exception("Invoice is deactivated.");
	        }

	        LOGGER.info("Service invoice with ID {} retrieved successfully", id);
	        return serviceInvoiceEntityToModelConverter.getFindByIdConverter(invoice);
	    }

	    // ✅ Fetch All Service Invoices (With Pagination)
	    public List<ServiceInvoiceResponseModel> findAllServiceInvoices(Integer page, Integer size) throws Exception {
	        LOGGER.info("Fetching service invoices for page {} with size {}", page, size);

	        try {
	            Page<ServiceInvoice> serviceInvoicePage = serviceInvoiceRepo.findAllActive(PageRequest.of(page, size));
	            List<ServiceInvoice> serviceInvoices = serviceInvoicePage.getContent();

	            if (serviceInvoices.isEmpty()) {
	                LOGGER.warn("No service invoices found on page {} with size {}", page, size);
	                throw new Exception("No service invoices found");
	            }

	            LOGGER.info("Successfully retrieved {} service invoices on page {}", serviceInvoices.size(), page);
	            return serviceInvoiceEntityToModelConverter.getfindAllConverter(serviceInvoices);
	        } catch (Exception e) {
	            LOGGER.error("Error while fetching service invoices: {}", e.getMessage(), e);
	            throw new Exception("Error while fetching service invoices", e);
	        }
	    }

	    // ✅ Count Total Service Invoices
	    public long countServiceInvoices() {
	        LOGGER.info("Counting all active service invoices");
	        return serviceInvoiceRepo.countActiveInvoices();
	    }

	    public void softDeleteById(Integer id) throws Exception {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        Optional<ServiceInvoice> invoiceOptional = serviceInvoiceRepo.findById(id);
	        if (invoiceOptional.isEmpty()) {
	            LOGGER.error("Invoice with ID {} does not exist", id);
	            throw new Exception("Invoice does not exist.");
	        }

	        ServiceInvoice serviceInvoice = invoiceOptional.get();
	        if (serviceInvoice.getIsDeleted() == 1) {
	            LOGGER.warn("Invoice ID {} is already deleted", id);
	            throw new Exception("Invoice already deleted.");
	        }

	        serviceInvoice.setIsDeleted(1);
	        serviceInvoiceRepo.save(serviceInvoice);

	        LOGGER.info("Invoice ID {} soft deleted successfully", id);
	    }
	    public void restoreById(Integer id, Integer status) throws Exception {
	        Optional<ServiceInvoice> invoiceOptional = serviceInvoiceRepo.findById(id);

	        if (invoiceOptional.isEmpty()) {
	            throw new RuntimeException("Invoice does not exist.");
	        }

	        ServiceInvoice serviceInvoice = invoiceOptional.get();

	        // Check if invoice is already active
	        if (serviceInvoice.getIsDeleted() == 0) {
	            throw new Exception("Invoice is already active.");
	        }

	        serviceInvoice.setIsDeleted(status);
	        serviceInvoiceRepo.save(serviceInvoice);
	    }

	   
}
