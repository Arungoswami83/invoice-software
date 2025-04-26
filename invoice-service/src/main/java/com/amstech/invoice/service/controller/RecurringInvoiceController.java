package com.amstech.invoice.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.request.model.ProformaInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.RecurringInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.RecurringInvoiceUpdateRequestModel;
import com.amstech.invoice.service.request.model.ServiceInvoiceSignupRequestModel;
import com.amstech.invoice.service.response.ResponseMessage;
import com.amstech.invoice.service.response.RestResponse;
import com.amstech.invoice.service.response.model.ProformaInvoiceResponseModel;
import com.amstech.invoice.service.response.model.RecurringInvoiceResponseModel;
import com.amstech.invoice.service.service.RecurringInvoiceService;
import com.amstech.invoice.service.service.ServiceInvoiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recurringinvoice")
public class RecurringInvoiceController {
	
    private  final Logger LOGGER = LoggerFactory.getLogger(RecurringInvoiceController.class);
//	@PostMapping("/create")
//    public ResponseMessage createProformaInvoice(@Valid @RequestBody ProformaInvoiceSignupRequestModel requestModel) {
//        LOGGER.info("Creating Proforma Invoice for Client ID: {}", requestModel.getClientId());
//
//        try {
//            ProformaInvoiceResponseModel responseModel = proformaInvoiceService.signup(requestModel);
//
//            if (responseModel == null) {
//                return ResponseMessage.build().withError("Proforma Invoice creation failed");
//            }
//
//            Map<String, Object> responseMap = new HashMap<>();
//            responseMap.put("invoice", responseModel);
//            responseMap.put("pdfUrl", responseModel.getPdfUrl());
//
//            LOGGER.info("Proforma Invoice Created Successfully for Client ID: {}", requestModel.getClientId());
//            return ResponseMessage.build().withSuccess("Proforma Invoice Created Successfully", responseMap);
//
//        } catch (DataIntegrityViolationException e) {
//            LOGGER.error("Duplicate Entry Error: {}", e.getMessage(), e);
//            return ResponseMessage.build().withError("Duplicate Entry: Invoice already exists");
//
//        } catch (IllegalArgumentException e) {
//            LOGGER.error("Invalid input: {}", e.getMessage(), e);
//            return ResponseMessage.build().withError("Invalid data provided");
//
//        } catch (Exception e) {
//            LOGGER.error("Error occurred while creating Proforma Invoice: {}", e.getMessage(), e);
//            return ResponseMessage.build().withError("Internal server error. Please try again later.");
//        }
//    }
    

	
	@Autowired
	private  RecurringInvoiceService recurringInvoiceService;
    @CrossOrigin(origins = "http://localhost:4200") // ✅ Method level par use karein

	@RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
    public RestResponse signup(@RequestBody RecurringInvoiceSignupRequestModel requestModel) {
        LOGGER.info("Saving recurring invoice data: {}", requestModel.getClientId());
        System.out.println("Client ID: " + requestModel.getClientId());
        System.out.println("Company ID: " + requestModel.getCompanyId());
        System.out.println("End Date: " + requestModel.getEndDate());
        System.out.println("Total Payable: " + requestModel.getTotalPayable());


        try {
            RecurringInvoiceResponseModel responseModel = recurringInvoiceService.signup(requestModel);
            LOGGER.info("Recurring Invoice created Successfully!");
            Map<String, Object> responseMap = new HashMap<>();
          responseMap.put("invoice", responseModel);
          responseMap.put("pdfUrl", responseModel.getpdfPath());

          LOGGER.info("Proforma Invoice Created Successfully for Client ID: {}", requestModel.getClientId());
          return RestResponse.build().withSuccess("recuuring Invoice Created Successfully", responseMap);

            
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Duplicate Entry Error: {}", e.getMessage(), e);
            return RestResponse.build().error("Duplicate Entry: Invoice already exists");

        } catch (Exception e) {
            LOGGER.error("Error occurred while signing up: {}", e.getMessage(), e);
            return RestResponse.build().error("Failed to save recurring invoice");
        }
    }

    // ✅ Update Recurring Invoice
    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
    public RestResponse update(@RequestBody RecurringInvoiceUpdateRequestModel requestModel) {
        LOGGER.info("Updating recurring invoice data: {}", requestModel.getId());

        try {
            RecurringInvoiceResponseModel responseModel = recurringInvoiceService.update(requestModel);
            LOGGER.info("Recurring Invoice updated Successfully!");

            return RestResponse.build().withSuccess("Recurring Invoice updated Successfully", responseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to update invoice {} :", e.getMessage(), e);
            return RestResponse.build().withError(e.getMessage());
        }
    }

    // ✅ Find Recurring Invoice by ID
    @RequestMapping(method = RequestMethod.GET, value = "/findByInvoiceId", produces = "application/json")
    public RestResponse findByInvoiceId(@RequestParam("id") Integer id) {
        LOGGER.info("Fetching recurring invoice by ID: {}", id);

        try {
            RecurringInvoiceResponseModel responseModel = recurringInvoiceService.findById(id);
            LOGGER.info("Invoice ID {} found successfully", id);

            return RestResponse.build().withSuccess("Invoice found successfully", responseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);
            return RestResponse.build().withError(e.getMessage());
        }
    }

    // ✅ Fetch All Recurring Invoices (With Pagination)
    @RequestMapping(method = RequestMethod.GET, value = "/findAllInvoices", produces = "application/json")
    public RestResponse findAllInvoices(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        LOGGER.info("Fetching all recurring invoices...");

        try {
            List<RecurringInvoiceResponseModel> responseModel = recurringInvoiceService.findAllRecurringInvoices(page, size);
            LOGGER.info("Recurring Invoice list found successfully!");

            long totalRecord = recurringInvoiceService.countRecurringInvoices();
            return RestResponse.build()
                    .withSuccess("Invoice list found successfully")
                    .withTotalRecords(totalRecord)
                    .withPageNumber(page)
                    .withPageSize(size)
                    .withData(responseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to find invoices due to: {}", e.getMessage(), e);
            return RestResponse.build().withError(e.getMessage());
        }
        
    }
	    @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteRecurringInvoice", produces = "application/json")
	    public RestResponse softDeleteRecurringInvoice(@RequestParam("id") Integer id) {
	        LOGGER.info("Soft deleting recurring invoice with ID: {}", id);

	        try {
	            recurringInvoiceService.softDeleteById(id);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);

	            return RestResponse.build().withSuccess("invoice deleted successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to delete invoice: {}", e.getMessage(), e);
	            return RestResponse.build().withError(e.getMessage());
	        }
	    }
	    @RequestMapping(method = RequestMethod.DELETE,value = "/RestoreDeleteProformaInvoice", produces = "application/json")
	    public RestResponse RestoreDeleteProformaInvoice(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        try {
	        	recurringInvoiceService.restoreById(id, status);
	            if(status==0) {
		            return RestResponse.build().withSuccess("invoice re-activate successfully");
	            }else {
		            return RestResponse.build().withSuccess("invoice de-activate successfully");

	            }
	        } catch (Exception e) {
	            LOGGER.error("Failed to restore invoice ID {}: {}", id);
	            return RestResponse.build().withError(e.getMessage());
	            }
}
	    
}
