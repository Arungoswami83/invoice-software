package com.amstech.invoice.service.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.SalesInvoices;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.SalesRepo;
import com.amstech.invoice.service.request.model.ClientSignupRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.request.model.CompanyUpdateRequestModel;
import com.amstech.invoice.service.request.model.SalesSignupRequestModel;
import com.amstech.invoice.service.request.model.SalesUpdateRequestModel;
import com.amstech.invoice.service.response.RestResponse;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;
import com.amstech.invoice.service.response.model.SalesInvoiceResponseModel;
import com.amstech.invoice.service.service.SalesService;

import io.swagger.v3.oas.annotations.Operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/sales")
public class SalesController {
	
	 private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
  @Autowired
  private SalesService salesService;	
  
  public SalesController() {
	  
	  logger.info("SalesController : object created");
  }

  @Operation(summary = "you can use this method for SalesInvoice/signup",description = "this is SalesInvoice signup")
  @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
  public RestResponse signup(@RequestBody SalesSignupRequestModel salesSignupRequestModel) {
      logger.info("Saving sales invoice for client ID: {}", salesSignupRequestModel.getClientId());
      try {
          SalesInvoiceResponseModel salesInvoiceResponseModel =salesService.signup(salesSignupRequestModel);
          return RestResponse.build().withSuccess("salesInvoice registyration successfully",salesInvoiceResponseModel);
      } catch (Exception e) {
    	  logger.error("Failed to save salesInvoices due to: {}", e.getMessage(), e);
          return RestResponse.build().withError(e.getMessage());
      }
  }
  

  @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
  public RestResponse update(@RequestBody SalesUpdateRequestModel salesUpdateRequestModel) {
      logger.info("Updating sales invoice ID: {}", salesUpdateRequestModel.getId());

      try {
    	  SalesInvoiceResponseModel salesInvoiceResponseModel = salesService.update(salesUpdateRequestModel);
          return RestResponse.build().withSuccess("salesInvoice updated successfully",salesInvoiceResponseModel);
      } catch (Exception e) {
    	  logger.error("Failed to update salesInvoices due to: {}", e.getMessage(), e);
          return RestResponse.build().withError(e.getMessage());
      }
  }

  
	

  @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
  public RestResponse findById(@RequestParam("id") Integer id) {
      logger.info("Fetching sales invoice details for ID: {}", id);

      try {
    	  SalesInvoiceResponseModel salesInvoiceResponseModel = salesService.findById(id);
          return RestResponse.build().withSuccess("sales invoice found successfully",salesInvoiceResponseModel);
      } catch (Exception e) {
    	  logger.error("Failed to find Sales Invoices due to: {}", e.getMessage(), e);
          return RestResponse.build().withError(e.getMessage());
      }
  }
  

  @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteById", produces = "application/json")
  public RestResponse softDeleteSalesInvoice(@RequestParam Integer id) {
      logger.info("Soft deleting sales invoice ID: {}", id);

      try {
          salesService.softDeleteById(id);
          return RestResponse.build().withSuccess("salesInvoice deleted successfully");
      } catch (Exception e) {
    	  logger.error("Failed to delete salesinvoices due to: {}", e.getMessage(), e);
          return RestResponse.build().withError(e.getMessage());
      }
  }
  
  @PutMapping("/restoreById")
  public ResponseEntity<String> restoreSalesInvoice(@RequestParam Integer id) {
      try {
          salesService.restoreById(id);
          return ResponseEntity.ok("Sales invoice restored successfully.");
      } catch (Exception e) {
          if (e.getMessage().equals("Sales invoice is already active.")) {
              return ResponseEntity.ok("Invoice is already active. No update needed.");
          }
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
  }


  @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
  public RestResponse findAllSalesInvoices(@RequestParam("page") Integer page, @RequestParam("size") Integer size)  {
      logger.info("Fetching all active sales invoices");
      try {
      List<SalesInvoiceResponseModel> salesInvoiceResponseModels = salesService.findAllActive(page,size);
      long totalRecord = salesService.count();
      return RestResponse.build().withSuccess("Companies retrieved successfully").withTotalRecords(totalRecord).withPageNumber(page).withPageSize(size).withData(salesInvoiceResponseModels);
  } catch (Exception e) {
	  e.printStackTrace();
      return RestResponse.build().withError(e.getMessage());
  }
}
}


		   
		




