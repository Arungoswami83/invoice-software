package com.amstech.invoice.service.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.request.model.ClientLoginRequestModel;
import com.amstech.invoice.service.request.model.ClientSignupRequestModel;
import com.amstech.invoice.service.request.model.ClientUpdateRequestModel;
import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.response.RestResponse;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.service.ClientService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/client")
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	 private ClientService clientService;
		
		public ClientController() {
	     System.out.println("ClientController: Object Created");
	    }
	
//
//		@Operation(summary = "you can use this method for client/signup",description = "this is client signup")
//		  @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
//		    public RestResponse signup(@RequestBody ClientSignupRequestModel clientRequestModel) {
//		        logger.info("Saving client data: {}", clientRequestModel.getFirstName());
//
//		        try {
//		            clientService.signup(clientRequestModel);
//		            return RestResponse.build().data(clientRequestModel).success(1452).message("client registration successfully");
//		        } catch (Exception e) {
//		        	 e.printStackTrace();
//			            return RestResponse.build().error(1457).message("failed to save client data due to"+e.getMessage());
//			        }
//		    }
//
//		    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
//		    public RestResponse userLogin(@RequestBody ClientLoginRequestModel clientLoginRequestModel) {
//		        logger.info("Login attempt for user: {}", clientLoginRequestModel.getUsername());
//
//		        try {
//		            clientService.login(clientLoginRequestModel);
//		            return RestResponse.build().data(clientLoginRequestModel).success(1452).message("client login successfully");
//		        } catch (Exception e) {
//		        	 e.printStackTrace();
//			            return RestResponse.build().error(1457).message("failed to login client data due to"+e.getMessage());
//			        }
//		    }
//		   
//		    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
//		    public RestResponse updateClient(@RequestBody ClientUpdateRequestModel requestModel) {
//		        logger.info("Updating client with ID: {}", requestModel.getId());
//
//		        try {
//		            clientService.updateClient(requestModel);
//		            return RestResponse.build().data(requestModel).success(1452).message("client updated successfully");
//		        } catch (Exception e) {
//		        	e.printStackTrace();
//		            return RestResponse.build().error(1457).message("failed to update client data due to"+e.getMessage());
//		        }
//		    }
//
//		    @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteById", produces = "application/json")
//		    public RestResponse softDeleteClient(@RequestParam Integer id) {
//		        logger.info("Soft deleting client with ID: {}", id);
//
//		        try {
//		            clientService.softDeleteById(id);
//		            return RestResponse.build().success(1452).message("client deleted successfully");
//		        } catch (Exception e) {
//		        	e.printStackTrace();
//		            return RestResponse.build().error(1457).message("failed to delete client data due to"+e.getMessage());
//		        }
//		    }
//		    
//		    @PutMapping("/restoreById")
//		    public ResponseEntity<String> restoreClient(@RequestParam Integer id) {
//		        try {
//		            clientService.restoreById(id);
//		            return ResponseEntity.ok("Client restored successfully.");
//		        } catch (Exception e) {
//		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		        }
//		    }
//
//		   
//		    @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
//		    public RestResponse findById(@RequestParam("id") Integer id) {
//		        logger.info("Fetching client details for ID: {}", id);
//
//		        try {
//		            ClientResponseModel clientResponseModel = clientService.findByClientId(id);
//		            return RestResponse.build().data(clientResponseModel).success(1452).message("client found successfully");
//		        } catch (Exception e) {
//		        	e.printStackTrace();
//		            return RestResponse.build().error(1457).message("failed to found client data due to"+e.getMessage());
//		        }
//		    }
//		    
//
//     @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
//     public ResponseEntity<RestResponse> findAllClients(@RequestParam("page") Integer page, 
//	 @RequestParam("size") Integer size) {
//		   logger.info("Fetching all client details, page: {}, size: {}", page, size);
//
//		  try {
//		 List<ClientResponseModel> clientResponseModels = clientService.findAll(page, size);
//		    long totalRecord = clientService.countAllClient();
//
// RestResponse response = RestResponse.build() .data(clientResponseModels).success(200) 
//		 .page(page).size(size).totalRecord(totalRecord).message("Clients retrieved successfully");
//
//		            return ResponseEntity.ok(response);
//
//		            } catch (Exception e) {
//		                logger.error("Error fetching client data", e);
//
//		                RestResponse errorResponse = RestResponse.build()
//		                        .error(500) 
//		                        .message("Failed to retrieve client data: " + e.getMessage());
//
//		                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//		            }
//		        }
		    }
	   
//		    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
//		    public RestResponse findAllClients(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
//		        logger.info("Fetching all client details");
//
//		        try {
//		            List<ClientResponseModel> clientResponseModels = clientService.findAll(page,size);
//		          long totalRecord = clientService.countAllClient();
//		            return RestResponse.build().data(clientResponseModels).success(1452).page(page).size(size).totalRecord(totalRecord).message("company updated successfully");
//		        } catch (Exception e) {
//		        	e.printStackTrace();
//		            return RestResponse.build().error(1457).message("failed to found client data due to"+e.getMessage());
//		        }
//		    }}

