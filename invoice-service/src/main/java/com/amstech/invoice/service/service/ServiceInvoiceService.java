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

   
	public void signup(ServiceInvoiceSignupRequestModel serviceInvoiceSignupRequestModel) throws Exception {
	    LOGGER.info("Signup request received for invoice: {}", serviceInvoiceSignupRequestModel.getInvoiceNumber());

	    Optional<Client> clientOptional = clientRepo.findById(serviceInvoiceSignupRequestModel.getClientId());
	    if (clientOptional.isEmpty()) {
	        LOGGER.error("Client with ID {} does not exist", serviceInvoiceSignupRequestModel.getClientId());
	        throw new Exception("Client does not exist.");
	    }
	    Client client = clientOptional.get();

	    ServiceInvoice serviceInvoice = serviceInvoiceModelToEntityConverter.getsaveConverter(serviceInvoiceSignupRequestModel);
	    serviceInvoice.setClient(client);  // ✅ Make sure client is set
	    serviceInvoiceRepo.save(serviceInvoice);
	}


	    public void update(ServiceInvoiceUpdateRequestModel requestModel) throws Exception {
	        LOGGER.info("Update request received for invoice ID: {}", requestModel.getId());

	        if (requestModel == null || requestModel.getId() == 0) {
	            LOGGER.error("Invoice ID is null or zero");
	            throw new RuntimeException("Invoice ID cannot be null or zero");
	        }

	        Optional<ServiceInvoice> existingInvoice = serviceInvoiceRepo.findById(requestModel.getId());
	        if (existingInvoice.isEmpty()) {
	            LOGGER.error("Invoice with ID {} not found", requestModel.getId());
	            throw new Exception("Invoice with ID " + requestModel.getId() + " does not exist");
	        }

	        ServiceInvoice serviceInvoice = existingInvoice.get();
	        serviceInvoice = serviceInvoiceModelToEntityConverter.getUpdateConverter(requestModel, serviceInvoice);
	        serviceInvoiceRepo.save(serviceInvoice);

	        LOGGER.info("Invoice ID {} updated successfully", requestModel.getId());
	    }

	    public ServiceInvoiceResponseModel findInvoiceById(Integer id) throws Exception {
	        LOGGER.info("Fetching invoice by ID: {}", id);

	        Optional<ServiceInvoice> serviceInvoice = serviceInvoiceRepo.findById(id);
	        if (serviceInvoice.isEmpty()) {
	            LOGGER.error("Invoice with ID {} not found", id);
	            throw new Exception("Invoice with ID " + id + " not found");
	        }
	        
	        ServiceInvoice Invoice = serviceInvoice.get();
	        if (Invoice.getIsDeleted() == 1) {
				throw new Exception("invoice is desctivate.");
			}

	        LOGGER.info("Invoice ID {} found successfully", id);
	        return serviceInvoiceEntityToModelConverter.getFindByIdConverter(serviceInvoice.get());
	    }

	    public List<ServiceInvoiceResponseModel> findAllInvoices(Integer page, Integer size) throws Exception {
	        LOGGER.info("Fetching invoices for page {} with size {}", page, size);

	        try {
	            Page<ServiceInvoice> serviceInvoicePage = serviceInvoiceRepo.findAllActive(PageRequest.of(page, size));
	            List<ServiceInvoice> serviceInvoices = serviceInvoicePage.getContent();

	            if (serviceInvoices.isEmpty()) {
	                LOGGER.warn("No invoices found in the database");
	                throw new Exception("No invoices found");
	            }

	            LOGGER.info("Fetched {} invoices successfully", serviceInvoices.size());
	            return serviceInvoiceEntityToModelConverter.getfindAllConverter(serviceInvoices);
	        } catch (Exception e) {
	            LOGGER.error("Error in findAllInvoices: {}", e.getMessage(), e);
	            throw new Exception("Error fetching invoices", e);
	        }
	    }


	    public long count() throws Exception {
		    return serviceInvoiceRepo.count();
		}

		 

	    public void softDeleteById(Integer id,Integer status) throws Exception {
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

	        serviceInvoice.setIsDeleted(status);
	        serviceInvoiceRepo.save(serviceInvoice);

	        LOGGER.info("Invoice ID {} soft deleted successfully", id);
	    }
	   
}
