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
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.request.model.SalesSignupRequestModel;
import com.amstech.invoice.service.request.model.SalesUpdateRequestModel;
import com.amstech.invoice.service.response.message.RestResponse;
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
	  
	  System.out.println("SalesController : object created");
  }
  
 
  @Operation(summary = "you can use this method for SalesInvoice/signup",description = "this is SalesInvoice signup")
  @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
  public RestResponse signup(@RequestBody SalesSignupRequestModel salesSignupRequestModel) {
      logger.info("Saving sales invoice for client ID: {}", salesSignupRequestModel.getClientId());

      try {
          salesService.signup(salesSignupRequestModel);
          return RestResponse.build().data(salesSignupRequestModel).success(1452).message("salesInvoice registyration successfully");
      } catch (Exception e) {
    	  e.printStackTrace();
          return RestResponse.build().error(1457).message("failed to save salesinvoice data due to"+e.getMessage());
      }
  }
 
  @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
  public RestResponse update(@RequestBody SalesUpdateRequestModel salesUpdateRequestModel) {
      logger.info("Updating sales invoice ID: {}", salesUpdateRequestModel.getId());

      try {
          salesService.updateSales(salesUpdateRequestModel);
          return RestResponse.build().data(salesUpdateRequestModel).success(1452).message("salesInvoice updated successfully");
      } catch (Exception e) {
    	  e.printStackTrace();
          return RestResponse.build().error(1457).message("failed to update salesinvoice data due to"+e.getMessage());
      }
  }

  
	
  @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
  public RestResponse getSalesInvoiceById(@RequestParam Integer id) {
      logger.info("Fetching sales invoice details for ID: {}", id);

      try {
          SalesInvoiceResponseModel response = salesService.findBySalesInvoiceId(id);
          return RestResponse.build().data(response).success(1452).message("salesInvoice found successfully");
      } catch (Exception e) {
    	  e.printStackTrace();
          return RestResponse.build().error(1457).message("failed to found salesinvoice data due to"+e.getMessage());
      }
  }
  @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteById", produces = "application/json")
  public RestResponse softDeleteSalesInvoice(@RequestParam Integer id) {
      logger.info("Soft deleting sales invoice ID: {}", id);

      try {
          salesService.softDeleteById(id);
          return RestResponse.build().success(1452).message("salesInvoice deleted successfully");
      } catch (Exception e) {
    	  e.printStackTrace();
          return RestResponse.build().error(1457).message("failed to deleted salesinvoice data due to"+e.getMessage());
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
      return RestResponse.build().data(salesInvoiceResponseModels).success(1452).page(page).size(size).totalRecord(totalRecord).message("salesInvoice found successfully");
  } catch (Exception e) {
	  e.printStackTrace();
      return RestResponse.build().error(1457).message("failed to found salesinvoice data due to"+e.getMessage());
  }
}
}


		   
		




