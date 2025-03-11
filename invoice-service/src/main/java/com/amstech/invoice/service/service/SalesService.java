package com.amstech.invoice.service.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.converter.entity.SalesModelToEntityConverter;
import com.amstech.invoice.service.converter.model.SalesEntityToModelConverter;
import com.amstech.invoice.service.entity.City;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.SalesInvoices;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.SalesRepo;
import com.amstech.invoice.service.request.model.ClientSignupRequestModel;
import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.SalesSignupRequestModel;
import com.amstech.invoice.service.request.model.SalesUpdateRequestModel;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;
import com.amstech.invoice.service.response.model.SalesInvoiceResponseModel;

import jakarta.transaction.Transactional;


@Service
public class SalesService {
	
	 private static final Logger logger = LoggerFactory.getLogger(SalesService.class);
	@Autowired
	private SalesRepo salesRepo;
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private SalesEntityToModelConverter salesEntityToModelConverter;
	@Autowired
	private SalesModelToEntityConverter salesModelToEntityConverter;
	

	  public SalesInvoiceResponseModel signup(SalesSignupRequestModel salesSignupRequestModel) throws Exception {
	      
		  Optional<Client> clientOptional = clientRepo.findById(salesSignupRequestModel.getClientId());
	        if (!clientOptional.isPresent()) {
	        	logger.error("client does not exist for ID: {}",salesSignupRequestModel.getClientId());
	            throw new Exception("Client dore not exists");
	            
	        }
	        
	        SalesInvoices salesInvoices = salesModelToEntityConverter.getsave(salesSignupRequestModel);	        
	        salesInvoices.setClient(clientOptional.get());
	        SalesInvoices salesSave = salesRepo.save(salesInvoices);
	        return salesEntityToModelConverter.getfindBySalesInvoiceId(salesSave);
	    }
	    public SalesInvoiceResponseModel update(SalesUpdateRequestModel salesUpdateRequestModel) throws Exception {
	        
	        Optional<SalesInvoices> optionalSalesInvoices = salesRepo.findById(salesUpdateRequestModel.getId());
	        if (!optionalSalesInvoices.isPresent()) {
	           
	            throw new Exception("Sales Invoice does not exist");
	        }

	        SalesInvoices salesInvoices = optionalSalesInvoices.get();
	        Client client = clientRepo.findById(salesUpdateRequestModel.getClientId())
	                .orElseThrow(() -> new Exception("Client does not exist"));
	        
	        salesInvoices = salesModelToEntityConverter.getupdate(salesUpdateRequestModel, salesInvoices);
	        SalesInvoices savedSales = salesRepo.save(salesInvoices);
	        return salesEntityToModelConverter.getfindBySalesInvoiceId(savedSales);
	       
	        
	       	    }

	    public SalesInvoiceResponseModel findById(Integer id)throws Exception {

	            Optional<SalesInvoices> salesOptional = salesRepo.findById(id);
	            
	            if (salesOptional.isEmpty()) {
		          
		            throw new Exception("Client does not exists");
		        }
	            SalesInvoices salesInvoice   = salesOptional.get();
	            
	            
	            if(salesInvoice.getIsDeleted() == 1) {
	     	       throw new Exception("Your account has been deactivated. Please contact the administrator for assistance.");
	     	        }
	            
	            return salesEntityToModelConverter.getfindBySalesInvoiceId(salesInvoice);
	    }
	            

	 
	    public void softDeleteById(Integer id) throws Exception {
	       
	        Optional<SalesInvoices> invoiceOptional = salesRepo.findById(id);

	        if (!invoiceOptional.isPresent()) {
	            logger.error("Attempted to delete non-existing {} id.",id);
	            throw new Exception("Sales Invoice does not exist.");
	        }

	        SalesInvoices invoice = invoiceOptional.get();
	        if (invoice.getIsDeleted() == 1) {
	            logger.warn("Sales Invoice already deleted.",id);
	            throw new Exception("Sales Invoice already deleted.");
	        }

	        invoice.setIsDeleted(1);
	        invoice.setUpdatedAt(Timestamp.from(Instant.now()));
	        salesRepo.save(invoice);
	        
	    }
	    @Transactional
	    public void restoreById(Integer id) throws Exception {
	        Optional<SalesInvoices> invoiceOptional = salesRepo.findById(id);

	        if (!invoiceOptional.isPresent()) {
	            throw new Exception("Sales invoice does not exist.");
	        }

	        SalesInvoices invoice = invoiceOptional.get();

	        if (invoice.getIsDeleted() == 0) {
	            return; 
	        }

	       
	        invoice.setIsDeleted(0);
	        salesRepo.save(invoice);
	    }

	    public List<SalesInvoiceResponseModel> findAllActive(Integer page , Integer size) {
	     
	    	 List<SalesInvoices> sales = salesRepo.findAll();
		        return salesEntityToModelConverter.getfindAllActiveConverter(sales);
	    
	    }
        public long count() throws Exception{
    	return salesRepo.count();
    }
	}
