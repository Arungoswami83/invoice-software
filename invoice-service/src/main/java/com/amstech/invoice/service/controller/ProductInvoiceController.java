package com.amstech.invoice.service.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.amstech.invoice.service.response.RestResponse;
import com.amstech.invoice.service.response.model.ProductInvoiceResponseModel;
import com.amstech.invoice.service.service.ProductInvoiceService;

import io.swagger.v3.oas.annotations.Operation;

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
	        LOGGER.info("Saving invoice data: {}", productInvoiceSignupRequestModel.getOrderNumber());

	        try {
	        	ProductInvoiceResponseModel ResponseModel =productInvoiceService.signup(productInvoiceSignupRequestModel);
	            LOGGER.info("Product Invoice created Successfully!");

	            return RestResponse.build().withSuccess("Product Invoice Created Successfully",ResponseModel );

	        } catch (DataIntegrityViolationException e) {
	            LOGGER.error("Duplicate Entry Error: {}", e.getMessage(), e);
	            return RestResponse.build().error("Duplicate Entry: Invoice already exists");

	        } catch (Exception e) {
	            LOGGER.error("Error occurred while signing up: {}", e.getMessage(), e);
	            return RestResponse.build().error("Failed to save invoice");
	        }
	    }

    
	    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
	    public RestResponse update(@RequestBody ProductInvoiceUpdateRequestModel productInvoiceUpdateRequestModel) {
	    	LOGGER.info("updating user data: {}" + productInvoiceUpdateRequestModel.getId());

	        try {
	           	   ProductInvoiceResponseModel ResponseModel= productInvoiceService.update(productInvoiceUpdateRequestModel);
	            LOGGER.info("Product Invoice updated Successfully!");
               return RestResponse.build().withSuccess("Product Invoice update Successfully",ResponseModel );
	        } catch (Exception e) {
	            LOGGER.error("Failed to update invoice {} :", e.getMessage(), e);

               return RestResponse.build().withError(e.getMessage());
	        }
	    }
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/findByInvoiceId", produces = "application/json")
	    public RestResponse findByInvoiceId(@RequestParam("id") Integer id) {
	        LOGGER.info("Fetching invoice by id: {}", id);

	        try {
	            ProductInvoiceResponseModel ResponseModel = productInvoiceService.findInvoiceById(id);
	            LOGGER.info("Invoice ID {} found successfully", id);

            return RestResponse.build().withSuccess("invoice found successfully", ResponseModel);
	        } catch (Exception e) {
	            LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);
            return RestResponse.build().withError(e.getMessage());
	        }
	    }


	    @RequestMapping(method = RequestMethod.GET, value = "/findAllInvoices", produces = "application/json")
	    public RestResponse findAllInvoices(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
	        LOGGER.info("Fetching all invoices...");

	        try {
	            List<ProductInvoiceResponseModel> ResponseModel = productInvoiceService.findAll(page,size);
	            LOGGER.info("Product Invoice find Successfully!");

	            long totalRecord = productInvoiceService.countAllInvoice();
	            return RestResponse.build().withSuccess("invoice list found successfully").withTotalRecords(totalRecord).withPageNumber(page).withPageSize(size).withData(ResponseModel);
	        } catch (Exception e) {
	            LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);

	            return RestResponse.build().withError(e.getMessage());
	        }
	    }
	    @RequestMapping(method = RequestMethod.DELETE,value = "/softDeleteByInvoiceId", produces = "application/json")
	    public RestResponse softDeleteProductInvoice(@RequestParam("id") Integer id) {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        try {
	            productInvoiceService.softDeleteById(id);
	            LOGGER.info("Invoice ID {} soft deleted successfully", id);
	            return RestResponse.build().withSuccess("invoice deleted successfully");
	        } catch (Exception e) {
	            LOGGER.error("Failed to delete invoice ID {}: {}", id, e.getMessage());
	            return RestResponse.build().withError(e.getMessage());
	            }
	 }
	    
	    @RequestMapping(method = RequestMethod.DELETE,value = "/RestoreDeleteProductInvoice", produces = "application/json")
	    public RestResponse RestoreDeleteProductInvoice(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
	        LOGGER.info("Soft delete request received for invoice ID: {}", id);

	        try {
	            productInvoiceService.restoreById(id, status);
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


	


