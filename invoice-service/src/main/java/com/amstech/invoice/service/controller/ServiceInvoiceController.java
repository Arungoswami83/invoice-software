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

import com.amstech.invoice.service.request.model.ServiceInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ServiceInvoiceUpdateRequestModel;
import com.amstech.invoice.service.response.message.RestResponse;
import com.amstech.invoice.service.response.model.ServiceInvoiceResponseModel;
import com.amstech.invoice.service.service.ServiceInvoiceService;

@RestController
@RequestMapping("/serviceinvoice")
public class ServiceInvoiceController {
    private  final Logger LOGGER = LoggerFactory.getLogger(ServiceInvoiceController.class);

	

    @Autowired
    private ServiceInvoiceService serviceInvoiceService;
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
    public RestResponse signup(@RequestBody ServiceInvoiceSignupRequestModel serviceInvoiceSignupRequestModel) {
        LOGGER.info("Request received: Signup for invoice number: {}", serviceInvoiceSignupRequestModel.getInvoiceNumber());

        try {
            serviceInvoiceService.signup(serviceInvoiceSignupRequestModel);
            LOGGER.info("Service Invoice created successfully!");

            return RestResponse.build().data(serviceInvoiceSignupRequestModel).success(200).message("Service Invoice Created Successfully");
        } catch (DataIntegrityViolationException e) { // Catch duplicate entry error
            LOGGER.warn("Duplicate Invoice Number: {}", serviceInvoiceSignupRequestModel.getInvoiceNumber());
            return RestResponse.build().error(400).message("Invoice Number already exists");
        } catch (Exception e) {
            LOGGER.error("Error during signup: {}", e.getMessage(), e);
            return RestResponse.build().error(500).message("Failed to save invoice");
        }
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
    public RestResponse update(@RequestBody ServiceInvoiceUpdateRequestModel requestModel) {
        LOGGER.info("Request received: Update invoice with ID: {}", requestModel.getId());

        try {
            serviceInvoiceService.update(requestModel);
            LOGGER.info("service Invoice update Successfully!");

            return RestResponse.build().data(requestModel).success(200).message(" service Invoice update Successfully");
        } catch (Exception e) {
            LOGGER.error("Error during update: {}", e.getMessage(), e);
            return RestResponse.build().error(500).message("Failed to update invoice");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByInvoiceId", produces = "application/json")
    public RestResponse findByInvoiceId(@RequestParam("id") Integer id) {
        LOGGER.info("Request received: Find invoice by ID: {}", id);

        try {
            ServiceInvoiceResponseModel responseModel = serviceInvoiceService.findInvoiceById(id); // Fixed Method Call
            LOGGER.info("Invoice ID {} found successfully", id);

            return RestResponse.build().data(responseModel).success(200).message("Service Invoice Retrieved Successfully");
        } catch (Exception e) {
            LOGGER.error("Error during findByInvoiceId: {}", e.getMessage(), e);
            return RestResponse.build().error(500).message("Failed to find invoice");
        }
    }

    @RequestMapping(value = "/findAllInvoices", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ServiceInvoiceResponseModel>> findAllInvoices(
            @RequestParam Integer page, 
            @RequestParam Integer size) {

        LOGGER.info("Fetching invoices for page {} with size {}", page, size);

        try {
            // Calling service method to get the list of invoices
            List<ServiceInvoiceResponseModel> invoices = serviceInvoiceService.findAllInvoices(page, size);
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            LOGGER.error("Error in findAllInvoices: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null); // Return an error status if there is an exception
        }
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/softDelete", produces = "application/json")
    public RestResponse softDelete(@RequestParam("id") Integer id ,@RequestParam("status") Integer status) { 
        LOGGER.info("Request received: Soft delete invoice with ID: {}", id);

        try {
            serviceInvoiceService.softDeleteById(id, status);
            LOGGER.info("Invoice ID {} soft deleted successfully", id);

            return RestResponse.build().success(200).message(" service Invoice delete Successfully");
        } catch (Exception e) {
            LOGGER.error("Error during softDelete: {}", e.getMessage(), e);
            return RestResponse.build().error(500).message("Failed to delete invoice");
        }
    }
}

