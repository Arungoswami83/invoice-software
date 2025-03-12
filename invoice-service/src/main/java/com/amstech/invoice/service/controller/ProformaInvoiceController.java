package com.amstech.invoice.service.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.request.model.ProformaInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProformaInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.RestResponse;
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
            ProformaInvoiceResponseModel responseModel = proformaInvoiceService.signup(requestModel);
            LOGGER.info("Proforma Invoice Created Successfully!");

            return RestResponse.build().withSuccess("Proforma Invoice Created Successfully", responseModel);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Duplicate Entry Error: {}", e.getMessage(), e);
            return RestResponse.build().error("Duplicate Entry: Invoice already exists");
        } catch (Exception e) {
            LOGGER.error("Error occurred while signing up: {}", e.getMessage(), e);
            return RestResponse.build().error("Failed to save invoice");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
    public RestResponse update(@RequestBody ProformaInvoiceUpdateRequestModel requestModel) {
        LOGGER.info("Updating proforma invoice data: {}", requestModel.getId());

        try {
            ProformaInvoiceResponseModel responseModel = proformaInvoiceService.update(requestModel);
            LOGGER.info("Proforma Invoice updated successfully!");

            return RestResponse.build().withSuccess("Proforma Invoice Updated Successfully", responseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to update invoice: {}", e.getMessage(), e);
            return RestResponse.build().withError(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByInvoiceId", produces = "application/json")
    public RestResponse findByInvoiceId(@RequestParam("id") Integer id) {
        LOGGER.info("Fetching proforma invoice by ID: {}", id);

        try {
            ProformaInvoiceResponseModel responseModel = proformaInvoiceService.findInvoiceById(id);
            LOGGER.info("Proforma Invoice ID {} found successfully", id);

            return RestResponse.build().withSuccess("Proforma Invoice Found Successfully", responseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to find invoice: {}", e.getMessage(), e);
            return RestResponse.build().withError(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
    public RestResponse findAllInvoices(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        LOGGER.info("Fetching all proforma invoices...");

        try {
            List<ProformaInvoiceResponseModel> responseList = proformaInvoiceService.findAll(page, size);
            long totalRecord = proformaInvoiceService.countAllInvoices();
            LOGGER.info("Fetched {} proforma invoices successfully", responseList.size());

            return RestResponse.build()
                    .withSuccess("Proforma Invoices Found Successfully")
                    .withTotalRecords(totalRecord)
                    .withPageNumber(page)
                    .withPageSize(size)
                    .withData(responseList);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch invoices: {}", e.getMessage(), e);
            return RestResponse.build().withError(e.getMessage());
        }
    }
    
	    @RequestMapping(method = RequestMethod.DELETE, value = "/softDelete", produces = "application/json")
	    public RestResponse softDeleteById(@RequestParam("id") Integer id) {
	        LOGGER.info("Soft deleting proforma invoice with ID: {}", id);

	        try {
	            proformaInvoiceService.softDeleteById(id);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);

	            return RestResponse.build().withSuccess("invoice deleted successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to soft delete invoice due to: {}", e.getMessage(), e);
	            return RestResponse.build().withError(e.getMessage());
	        }
	    }
	    
	    @RequestMapping(method = RequestMethod.DELETE,value = "/RestoreDeleteProformaInvoice", produces = "application/json")
	    public RestResponse RestoreDeleteProformaInvoice(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        try {
	            proformaInvoiceService.restoreById(id, status);
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
