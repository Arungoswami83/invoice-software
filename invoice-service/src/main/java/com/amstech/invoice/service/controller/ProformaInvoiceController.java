package com.amstech.invoice.service.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.request.model.ProformaInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProformaInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.message.RestResponse;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;
import com.amstech.invoice.service.service.ProformaInvoiceService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/proformainvoice")
public class ProformaInvoiceController {
    private  final Logger LOGGER = LoggerFactory.getLogger(ProformaInvoiceController.class);

	@Autowired
	private  ProformaInvoiceService proformaInvoiceService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
	public RestResponse signup(@RequestBody ProformaInvoiceSignupRequestModel requestModel) {
	    LOGGER.info("Saving invoice data for Client ID: {}", requestModel.getClientId());

	    try {
	        proformaInvoiceService.Signup(requestModel);
	        LOGGER.info("success");
	       

	        return RestResponse.build().data(requestModel).message("Proforma Invoice Created Successfully");
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while signing up: {}", e.getMessage(), e);

	        // Return a more specific error code and message, based on the error type
	        return RestResponse.build()
	                .error(500)  // Internal server error (or use 400 for bad input)
	                .message("Failed to save invoice: " + e.getMessage());
	    }
	}


	    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	    public RestResponse updateProformaInvoice(@RequestBody ProformaInvoiceUpdateRequestModel requestModel) {
	        LOGGER.info("Updating invoice data for Invoice ID: {}", requestModel.getId());

	        try {
	            proformaInvoiceService.update(requestModel);
	            LOGGER.info("proforma Invoice update Successfully!");

	            return RestResponse.build().data(requestModel).success(200).message(" Proforma Invoice update Successfully");
	        }  catch (Exception e) {
	            LOGGER.error("Failed to update invoices due to: {}", e.getMessage(), e);

	            return RestResponse.build().error(500).message("Failed to update invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/findByInvoiceId", produces = "application/json")
	    public RestResponse findByInvoiceId(@RequestParam("id") Integer id) {
	        LOGGER.info("Fetching proforma invoice by ID: {}", id);

	        try {
	            ProformaInvoiceResponseModel responseModel = proformaInvoiceService.findById(id);
	            LOGGER.info("proforma Invoice find Successfully!");

	            return RestResponse.build().data(responseModel).success(200).message(" Proforma Invoice find Successfully");
	        }  catch (Exception e) {
	            LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
	    public RestResponse findAllInvoices(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
	        LOGGER.info("Fetching all proforma invoices...");

	        try {
	            List<ProformaInvoiceResponseModel> responseList = proformaInvoiceService.findAllInvoices(page,size);
	            long totalRecord = proformaInvoiceService.count();
	            LOGGER.info("Successfully fetched {} invoices.", responseList.size());
	            return RestResponse.build().data(responseList).success(200).page(page).size(size).totalRecord(totalRecord).message("find all invoice successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to fetch invoices due to: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.DELETE, value = "/softDelete", produces = "application/json")
	    public RestResponse softDeleteById(@RequestParam("id") Integer id) {
	        LOGGER.info("Soft deleting proforma invoice with ID: {}", id);

	        try {
	            proformaInvoiceService.softDeleteById(id);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);

	            return RestResponse.build().success(200).message(" Proforma Invoice delete Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to soft delete invoice due to: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to delete invoice");
	        }
	    }
}
