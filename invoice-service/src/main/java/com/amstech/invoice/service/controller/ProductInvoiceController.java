package com.amstech.invoice.service.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.request.model.ProductInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProductInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.message.RestResponse;
import com.amstech.invoice.service.response.model.ProductInvoiceResponseModel;
import com.amstech.invoice.service.service.ProductInvoiceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RestController
@RequestMapping("/productinvoice")
public class ProductInvoiceController {
	
	private final  Logger  LOGGER = LoggerFactory.getLogger(ProductInvoiceController.class);
	public ProductInvoiceController() {
		LOGGER.info("ProductInvoiceController : object created");
	}
	
	    @Autowired
	    private ProductInvoiceService productInvoiceService;
	    
	    @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
	    public RestResponse signup(@RequestBody ProductInvoiceSignupRequestModel productInvoiceSignupRequestModel) {
	        LOGGER.info("Saving user data: {}", productInvoiceSignupRequestModel.getId());

	        try {
	            productInvoiceService.signup(productInvoiceSignupRequestModel);
	            LOGGER.info("Product Invoice created Successfully!");

	            return RestResponse.build().data(productInvoiceSignupRequestModel).success(200).message("Product Invoice Created Successfully");

	        } catch (DataIntegrityViolationException e) {
	            LOGGER.error("Duplicate Entry Error: {}", e.getMessage(), e);
	            return RestResponse.build().error(400).message("Duplicate Entry: Invoice already exists");

	        } catch (Exception e) {
	            LOGGER.error("Error occurred while signing up: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to save invoice");
	        }
	    }

	    
	    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	    public RestResponse update(@RequestBody ProductInvoiceUpdateRequestModel productInvoiceUpdateRequestModel) {
	    	LOGGER.info("updating user data: {}" + productInvoiceUpdateRequestModel.getId());

	        try {
	            productInvoiceService.update(productInvoiceUpdateRequestModel);
	            LOGGER.info("Product Invoice updated Successfully!");

	            return RestResponse.build().data(productInvoiceUpdateRequestModel).success(200).message("Product Invoice updated Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to update invoice {} :", e.getMessage(), e);

	            return RestResponse.build().error(500).message("Failed to update invoice");
	        }
	    }
	    @RequestMapping(method = RequestMethod.GET, value = "/findByInvoiceId", produces = "application/json")
	    public RestResponse findByInvoiceId(@RequestParam("id") Integer id) {
	        LOGGER.info("Fetching invoice by id: {}", id);

	        try {
	            ProductInvoiceResponseModel productInvoiceResponeModel = productInvoiceService.findInvoiceById(id);
	            LOGGER.info("Invoice ID {} found successfully", id);

	            return RestResponse.build().data(productInvoiceResponeModel).success(200).message("Product Invoice find Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/findAllInvoices", produces = "application/json")
	    public RestResponse findAllInvoices(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
	        LOGGER.info("Fetching all invoices...");

	        try {
	            List<ProductInvoiceResponseModel> invoices = productInvoiceService.findAll(page,size);
	            LOGGER.info("Product Invoice find Successfully!");

	            long totalRecord = productInvoiceService.countAllInvoice();
	            return RestResponse.build().data(invoices).success(200).page(page).size(size).totalRecord(totalRecord).message("find all invoice successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);

	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }
	    @RequestMapping(method = RequestMethod.DELETE,value = "/softDeleteByInvoiceId", produces = "application/json")
	    public RestResponse softDeleteProductInvoice(@RequestParam("id") Integer id, 
	                                                 @RequestParam("status") Integer status) {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        try {
	            productInvoiceService.softDeleteById(id, status);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);
	            return RestResponse.build().success(200).message("Product Invoice deleted Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to delete invoice ID {}: {}", id, e.getMessage());
	            return RestResponse.build().error(500).message("Failed to delete invoice");
	        }
	    }


}

	    

	


