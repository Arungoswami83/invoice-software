package com.amstech.invoice.service.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amstech.invoice.service.converter.entity.ProductInvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.ProductInvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.repo.ProductInvoiceRepo;
import com.amstech.invoice.service.request.model.ProductInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProductInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.model.ProductInvoiceResponseModel;


@Service

public class ProductInvoiceService {

	@Autowired
	private ProductInvoiceRepo productInvoiceRepo;
	@Autowired
	private ProductInvoiceModelToEntityConverter productInvoiceModelToEntityConverter;
	@Autowired
	private ProductInvoiceEntityToModelConverter productInvoiceEntityToModelConverter;

    private  final Logger LOGGER = LoggerFactory.getLogger(ProductInvoiceService.class);


    public ProductInvoiceResponseModel signup(ProductInvoiceSignupRequestModel productInvoiceSignupRequestModel) throws Exception {
        if (productInvoiceSignupRequestModel == null) {
            LOGGER.error("Signup request failed: Request Model is null");
            throw new Exception("Request Model cannot be null");
        }

        LOGGER.debug("Converting request model to entity for user: {}", productInvoiceSignupRequestModel.getOrderNumber());

        ProductInvoice productInvoice = productInvoiceModelToEntityConverter.getSignupConverter(productInvoiceSignupRequestModel);
        ProductInvoice save = productInvoiceRepo.save(productInvoice);

       return  productInvoiceEntityToModelConverter.getfindByIdConverter(productInvoice);
    		}



    public ProductInvoiceResponseModel update(ProductInvoiceUpdateRequestModel productInvoiceUpdateRequestModel) throws Exception {
    	
     Optional<ProductInvoice> InvoiceOptional = productInvoiceRepo.findById(productInvoiceUpdateRequestModel.getId());
    	   if (InvoiceOptional.isEmpty()) {
            throw new Exception("invoice does not exist.");
         }
 
       ProductInvoice productInvoice = InvoiceOptional.get();

        LOGGER.info("Updating invoice with ID: {}", productInvoiceUpdateRequestModel.getId());

        Optional<ProductInvoice> productInvoiceOptional = productInvoiceRepo.findById(productInvoiceUpdateRequestModel.getId());

        if (!productInvoiceOptional.isPresent()) {
            LOGGER.error("Product invoice with ID {} does not exist.", productInvoiceUpdateRequestModel.getId());
            throw new Exception("Product invoice does not exist.");
        }

     
        productInvoice = productInvoiceModelToEntityConverter.getUpdateConverter(productInvoiceUpdateRequestModel, productInvoice);
        productInvoiceRepo.save(productInvoice);

        LOGGER.info("Invoice with ID {} updated successfully.", productInvoiceUpdateRequestModel.getId());

        return productInvoiceEntityToModelConverter.getfindByIdConverter(productInvoice);
    }

    public ProductInvoiceResponseModel findInvoiceById(Integer id) throws Exception {
        LOGGER.info("Fetching invoice by ID: {}", id);

        Optional<ProductInvoice> productOptional = productInvoiceRepo.findById(id);

        if (!productOptional.isPresent()) {
            LOGGER.error("Invoice with ID {} does not exist.", id);
            throw new Exception("Invoice does not exist.");
        }

        ProductInvoice productInvoice = productOptional.get();
        LOGGER.info("Invoice with 	ID {} fetched successfully.", id);
        if (productInvoice.getIsDeleted() == 1) {
            LOGGER.warn("Invoice with ID {} is already deleted.", id);
            throw new Exception("Invoice already deleted.");
        }

        return productInvoiceEntityToModelConverter.getfindByIdConverter(productInvoice);
    }

    public List<ProductInvoiceResponseModel> findAll(Integer page,Integer size) throws Exception {
    	
        LOGGER.info("Fetching all invoices...");

        Page<ProductInvoice> productInvoicePage = productInvoiceRepo.findAllActive(PageRequest.of(page, size));
        LOGGER.info("Fetched {} invoices successfully");
        List<ProductInvoice> productInvoiceList = productInvoicePage.getContent();
        LOGGER.info("Fetched {} invoices successfully", productInvoiceList.size());


        return productInvoiceEntityToModelConverter.getfindAllConverter(productInvoiceList);
    }
        public long countAllInvoice()throws Exception  {
        	return productInvoiceRepo.countAllInvoice();
        }
    
    
   
    public void softDeleteById(Integer id) throws Exception {
        LOGGER.info("Attempting to soft delete invoice with ID: {}", id);

        Optional<ProductInvoice> invoiceOptional = productInvoiceRepo.findById(id);
        
        if (!invoiceOptional.isPresent()) {
            LOGGER.error("Invoice with ID {} does not exist", id);
            throw new Exception("Invoice does not exist.");
        }

        ProductInvoice productInvoice = invoiceOptional.get();

        if (productInvoice.getIsDeleted() == 1) {
            LOGGER.warn("Invoice with ID {} is already deleted", id);
            throw new Exception("Invoice already deleted.");
        }

        productInvoice.setIsDeleted(1);
        productInvoiceRepo.save(productInvoice);
        LOGGER.info("Invoice with ID {} successfully soft deleted", id);
    }
    public void restoreById(Integer id, Integer status) throws Exception {
        Optional<ProductInvoice> invoiceOptional = productInvoiceRepo.findById(id);

        if (invoiceOptional.isEmpty()) {
            throw new RuntimeException("Invoice does not exist.");
        }

        ProductInvoice productInvoice = invoiceOptional.get();

        // Check if invoice is already active
        if (productInvoice.getIsDeleted() == 0) {
            throw new Exception("Invoice is already active.");
        }

        productInvoice.setIsDeleted(status);
        productInvoiceRepo.save(productInvoice);
    }

}



