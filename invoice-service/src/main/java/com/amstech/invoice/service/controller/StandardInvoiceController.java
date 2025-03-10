package com.amstech.invoice.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.request.model.StandardInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.StandardInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.message.RestResponse;
import com.amstech.invoice.service.response.model.StandardInvoiceResponseModel;
import com.amstech.invoice.service.service.StandardInvoiceService;

@RestController
@RequestMapping("/standardinvoice")
public class StandardInvoiceController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(StandardInvoiceController.class);
	
	@Autowired
	private StandardInvoiceService standardInvoiceService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
	public RestResponse signup(@RequestBody StandardInvoiceSignupRequestModel requestModel) {
	    LOGGER.info("Request received: Signup for invoice number: {}", requestModel.getInvoiceNumber());

	    try {
	        standardInvoiceService.signup(requestModel);
	        LOGGER.info("Standard Invoice created successfully!");

	        return RestResponse.build().data(requestModel).success(200).message("Standard Invoice Created Successfully");
	    } catch (DataIntegrityViolationException e) { // Catch duplicate entry error
	        LOGGER.warn("Duplicate Invoice Number: {}", requestModel.getInvoiceNumber());
	        return RestResponse.build().error(400).message("Invoice Number already exists");
	    } catch (Exception e) {
	        LOGGER.error("Error during signup: {}", e.getMessage(), e);
	        return RestResponse.build().error(500).message("Failed to save invoice");
	    }
	}

	 @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	    public RestResponse update(@RequestBody StandardInvoiceUpdateRequestModel requestModel) {
	        LOGGER.info("Updating Invoice ID: {}" + requestModel.getId());

	        try {
	            standardInvoiceService.update(requestModel); 
	            LOGGER.info("standard Invoice update Successfully!");

	            return RestResponse.build().data(requestModel).success(200).message(" Standard Invoice update Successfully");
	        } catch (Exception e) {
	        	LOGGER.error("error during update {}" +e.getMessage(),e);
	            return RestResponse.build().error(500).message("Failed to update invoice");
	        }
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
	    public RestResponse getInvoiceById(@RequestParam("id") Integer id) {
	        LOGGER.info("Fetching Standard Invoice by ID: {}", id);

	        try {
	            StandardInvoiceResponseModel response = standardInvoiceService.findInvoiceById(id);
	            LOGGER.info("Invoice ID {} found successfully", id);
	            return RestResponse.build().data(response).success(200).message(" Standard Invoice Find Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Error fetching invoice ID {}: {}", id, e.getMessage());
	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/findAllInvoices", produces = "application/json")
	    public RestResponse findAllInvoices (@RequestParam("page") Integer page,@RequestParam("size") Integer size){
	        LOGGER.info("Fetching all Standard Invoices");

	        try {
	            List<StandardInvoiceResponseModel> invoices = standardInvoiceService.findAllInvoices(page, size);
	            List<StandardInvoiceResponseModel> totalRecord = standardInvoiceService.findAllInvoices(page, size);
	            LOGGER.info("Fetched {} invoices successfully", invoices.size());


	            return RestResponse.build().data(invoices).success(200).message(" Standard Invoice Find Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to fetch invoices: {}", e.getMessage());
	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteStandardInvoice")
	    public RestResponse softDeleteRecurringInvoice(@RequestParam("id") Integer id, 
	    		@RequestParam("status") Integer status) {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        try {
	            standardInvoiceService.softDeleteById(id, status);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);
	            return RestResponse.build().success(200).message(" Standard Invoice deleted Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to delete invoice ID {}: {}", id, e.getMessage());
	            return RestResponse.build().error(500).message("Failed to delete invoice");
	        }
	    }
}
