package com.amstech.invoice.service.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.request.model.RecurringInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.RecurringInvoiceUpdateRequestModel;
import com.amstech.invoice.service.request.model.ServiceInvoiceSignupRequestModel;
import com.amstech.invoice.service.response.message.RestResponse;
import com.amstech.invoice.service.response.model.RecurringInvoiceResponseModel;
import com.amstech.invoice.service.service.RecurringInvoiceService;
import com.amstech.invoice.service.service.ServiceInvoiceService;

@RestController
@RequestMapping("/recurringinvoice")
public class RecurringInvoiceController {
	
    private  final Logger LOGGER = LoggerFactory.getLogger(RecurringInvoiceController.class);

	
	@Autowired
	private  RecurringInvoiceService recurringInvoiceService;
	   @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	    public ResponseEntity<RestResponse> signup(@RequestBody RecurringInvoiceSignupRequestModel requestModel) {
	        try {
	            recurringInvoiceService.signup(requestModel);
	            return ResponseEntity.ok(RestResponse.build().data(requestModel).success(200).message("Recurring Invoice Created Successfully"));
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body(RestResponse.build().error(500).message("Failed to save invoice: " + e.getMessage()));
	        }
	    }
	    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	    public RestResponse update(@RequestBody RecurringInvoiceUpdateRequestModel requestModel) {
	        LOGGER.info("Updating invoice data for ID: {}", requestModel.getId());

	        try {
	            recurringInvoiceService.update(requestModel);
	            LOGGER.info("recurring Invoice update Successfully!");

	            return RestResponse.build().data(requestModel).success(200).message(" Recurring Invoice update Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to update invoice data: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to update invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/findByInvoiceId", produces = "application/json")
	    public RestResponse findByInvoiceId(@RequestParam("id") Integer id) {
	        LOGGER.info("Fetching recurring invoice by ID: {}", id);

	        try {
	            RecurringInvoiceResponseModel responseModel = recurringInvoiceService.findById(id);
	            LOGGER.info("Invoice ID {} found successfully", id);

	            return RestResponse.build().data(responseModel).success(200).message(" Recurring Invoice find Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to find invoice: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
	    public RestResponse findAllInvoices (@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
	        LOGGER.info("Fetching all recurring invoices...");

	        try {
	            List<RecurringInvoiceResponseModel> responseList = recurringInvoiceService.findAllRecurringInvoices(page,size);
	            long totalRecord = recurringInvoiceService.countRecurringInvoices();
	            LOGGER.info("recurring Invoice find Successfully!");


	            return RestResponse.build().data(responseList).success(200).page(page).size(size).totalRecord(totalRecord).message("find all invoice successfully");
	            
	        } catch (Exception e) {
	            LOGGER.error("Failed to fetch invoices: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to find invoice");
	        }
	    }

	    @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteRecurringInvoice", produces = "application/json")
	    public RestResponse softDeleteRecurringInvoice(@RequestParam("id") Integer id) {
	        LOGGER.info("Soft deleting recurring invoice with ID: {}", id);

	        try {
	            recurringInvoiceService.softDeleteById(id);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);

	            return RestResponse.build().success(200).message(" Recurring Invoice deleted Successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to delete invoice: {}", e.getMessage(), e);
	            return RestResponse.build().error(500).message("Failed to delete invoice");
	        }
	    }
}
