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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.request.model.StandardInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.StandardInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.RestResponse;
import com.amstech.invoice.service.response.model.StandardInvoiceResponseModel;
import com.amstech.invoice.service.service.StandardInvoiceService;

@RestController
@RequestMapping("/standardinvoice")
public class StandardInvoiceController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(StandardInvoiceController.class);
	
	@Autowired
	private StandardInvoiceService standardInvoiceService;
	
    @CrossOrigin(origins = "http://localhost:4200") // ✅ Method level par use karein

	   
	    @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
	    public RestResponse signup(@RequestBody StandardInvoiceSignupRequestModel requestModel) {
	        LOGGER.info("Saving standard invoice data: {}", requestModel.getInvoiceNumber());

	        try {
	            StandardInvoiceResponseModel responseModel = standardInvoiceService.signup(requestModel);
	            LOGGER.info("Standard Invoice created Successfully!");
	            Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("invoice", responseModel);
                responseMap.put("pdfUrl", responseModel.getPdfPath());
	            return RestResponse.build().withSuccess("Standard Invoice Created Successfully", responseModel);
	        } catch (DataIntegrityViolationException e) {
	            LOGGER.error("Duplicate Entry Error: {}", e.getMessage(), e);
	            return RestResponse.build().withError("Duplicate Entry: Invoice already exists");
	        } catch (Exception e) {
	            LOGGER.error("Error occurred while signing up: {}", e.getMessage(), e);
	            return RestResponse.build().withError("Failed to save standard invoice");
	        }
	    }

	    // ✅ Update API
	    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	    public RestResponse update(@RequestBody StandardInvoiceUpdateRequestModel requestModel) {
	        LOGGER.info("Updating standard invoice data: {}", requestModel.getId());

	        try {
	            StandardInvoiceResponseModel responseModel = standardInvoiceService.update(requestModel);
	            LOGGER.info("Standard Invoice updated Successfully!");
	            return RestResponse.build().withSuccess("Standard Invoice updated Successfully", responseModel);
	        } catch (Exception e) {
	            LOGGER.error("Failed to update invoice {} :", e.getMessage(), e);
	            return RestResponse.build().withError(e.getMessage());
	        }
	    }

	    // ✅ Find By ID API
	    @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
	    public RestResponse findById(@RequestParam("id") Integer id) {
	        LOGGER.info("Fetching standard invoice by ID: {}", id);

	        try {
	            StandardInvoiceResponseModel responseModel = standardInvoiceService.findInvoiceById(id);
	            LOGGER.info("Invoice ID {} found successfully", id);
	            return RestResponse.build().withSuccess("Invoice found successfully", responseModel);
	        } catch (Exception e) {
	            LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);
	            return RestResponse.build().withError(e.getMessage());
	        }
	    }

	    // ✅ Fetch All Invoices (With Pagination)
	    @RequestMapping(method = RequestMethod.GET, value = "/findAllInvoices", produces = "application/json")
	    public RestResponse findAllInvoices(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
	        LOGGER.info("Fetching all standard invoices...");

	        try {
	            List<StandardInvoiceResponseModel> responseModel = standardInvoiceService.findAllInvoices(page, size);
	            LOGGER.info("Standard Invoice list found successfully!");
	            
	            long totalRecord = standardInvoiceService.countInvoices();
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
	    @RequestMapping(method = RequestMethod.DELETE, value = "/softDelete", produces = "application/json")
	    public RestResponse softDelete(@RequestParam("id") Integer id) { 
	        LOGGER.info("Request received: Soft delete invoice with ID: {}", id);

	        try {
	            standardInvoiceService.softDeleteById(id);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);

	            return RestResponse.build().withSuccess("invoice deleted successfully");
	        } catch (Exception e) {
	            LOGGER.error("Error during softDelete: {}", e.getMessage(), e);
	            return RestResponse.build().withError(e.getMessage());
	        }
	    }
	    @RequestMapping(method = RequestMethod.DELETE,value = "/RestoreDeleteProformaInvoice", produces = "application/json")
	    public RestResponse RestoreDeleteProformaInvoice(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        try {
	        	standardInvoiceService.restoreById(id, status);
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