package com.amstech.invoice.service.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;

import com.amstech.invoice.service.request.model.ClientLoginRequestModel;
import com.amstech.invoice.service.request.model.ClientSignupRequestModel;
import com.amstech.invoice.service.request.model.ClientUpdateRequestModel;
import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.response.RestResponse;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;
import com.amstech.invoice.service.service.ClientService;

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
			logger.info("ClientController: Object Created");
	    }
	    @CrossOrigin(origins = "http://localhost:4200") // ✅ Method level par use karein


		@Operation(summary = "you can use this method for client/signup",description = "this is client signup")
		  @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
		    public RestResponse signup(@RequestBody ClientSignupRequestModel clientSignupRequestModel) {
		        logger.info("Saving client data: {}", clientSignupRequestModel.getFirstName());

		        try {
		        	ClientResponseModel clientResponseModel = clientService.signup(clientSignupRequestModel);
		            return RestResponse.build().withSuccess("client registration successfully",clientResponseModel);
		        } catch (Exception e) {
		        	logger.error("Failed to save client due to: {}", e.getMessage(), e);
			            return RestResponse.build().withError(e.getMessage());
			        }
		    }

		    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json", produces = "application/json")
		    public RestResponse clientLogin(@RequestBody ClientLoginRequestModel clientLoginRequestModel) {
		      try {
		    	  logger.info("Login with username: {}", clientLoginRequestModel.getUsername());
		    	  ClientResponseModel clientResponseModel =  clientService.login(clientLoginRequestModel);
		            return RestResponse.build().withSuccess("client login successfully");
		        } catch (Exception e) {
		        	logger.error("Failed to login client due to: {}", e.getMessage(), e);
			            return RestResponse.build().withError(e.getMessage());
			        }
		    }
		   
		    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
		    public RestResponse updateClient(@RequestBody ClientUpdateRequestModel clientUpdateRequestModel) {
		        logger.info("Updating client with ID: {}", clientUpdateRequestModel.getId());

		        try {
		        	ClientResponseModel clientResponseModel =  clientService.updateClient(clientUpdateRequestModel);
		           return RestResponse.build().withSuccess("client updated successfully",clientResponseModel);
		        } catch (Exception e) {
	            logger.error("Failed to update client due to: {}", e.getMessage(), e);
		            return RestResponse.build().withError(e.getMessage());
		        }
		    }
		  
		    @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteById", produces = "application/json")
		    public RestResponse softDeleteClient(@RequestParam("clientid") Integer id) {
		        logger.info("Soft deleting client with ID: {}", id);

		        try {
		            clientService.softDeleteById(id);
		            return RestResponse.build().withSuccess("client deleted successfully");
		        } catch (Exception e) {
		        	logger.error("Failed to delete client due to: {}", e.getMessage(), e);
		            return RestResponse.build().withError(e.getMessage());
		        }
		    }
		    
		    @PutMapping("/restoreById")
		    public ResponseEntity<String> restoreClient(@RequestParam Integer id) {
		        try {
		            clientService.restoreById(id);
		            return ResponseEntity.ok("Client restored successfully.");
		        } catch (Exception e) {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		        }
		    }

		    
		    @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
		    public RestResponse findById(@RequestParam("id") Integer id) {
		        logger.info("Fetching client details for ID: {}", id);

		        try {
		            ClientResponseModel clientResponseModel = clientService.findByClientId(id);
		            return RestResponse.build().withSuccess("client found successfully",clientResponseModel);
		        } catch (Exception e) {
		        	logger.error("Failed to find user due to: {}", e.getMessage(), e);		
		        	return RestResponse.build().withError(e.getMessage());
		        }
		    }
		    
     @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
     public RestResponse findAllClients(@RequestParam("page") Integer page,  @RequestParam("size") Integer size) {
		   logger.info("Fetching all client details, page: {}, size: {}");
		  try {
		 List<ClientResponseModel> clientResponseModels = clientService.findAllClient(page, size);
		    long totalRecord = clientService.countAllClient();
		    return RestResponse.build().withSuccess("Clients found successfully").withTotalRecords(totalRecord).withPageNumber(page).withPageSize(size).withData(clientResponseModels);

		   } catch (Exception e) {
		       logger.error("Failed to find client data {} ", e.getMessage(),e);
		       return RestResponse.build().withError(e.getMessage());
		            }
		        }
		    }

