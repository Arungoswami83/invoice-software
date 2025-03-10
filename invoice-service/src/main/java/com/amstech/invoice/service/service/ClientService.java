	package com.amstech.invoice.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.entity.City;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.repo.CityRepo;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.request.model.ClientLoginRequestModel;
import com.amstech.invoice.service.request.model.ClientSignupRequestModel;
import com.amstech.invoice.service.request.model.ClientUpdateRequestModel;
import com.amstech.invoice.service.response.model.ClientResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;




@Service
public class ClientService {
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	
	
	    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

	    public void signup(ClientSignupRequestModel clientSignupRequestModel) throws Exception {
	        logger.info("Signup request received for username: {}", clientSignupRequestModel.getUsername());

	        Optional<City> existingCity = cityRepo.findById(clientSignupRequestModel.getCityId());
	        Client client = new Client();

	        client.setFirstName(clientSignupRequestModel.getFirstName());
	        client.setLastName(clientSignupRequestModel.getLastName());
	        client.setEmail(clientSignupRequestModel.getEmailAddress());
	        client.setCompanyName(clientSignupRequestModel.getCompanyName());
	        client.setBusinessName(clientSignupRequestModel.getBusinessName());
	        client.setLinkedinProfileUrl(clientSignupRequestModel.getLinkedinProfileUrl());
	        client.setBillingAddress(clientSignupRequestModel.getBillingAddress());
	        client.setSpecificRegistrationDetails(clientSignupRequestModel.getSpecificRegistrationDetails());
	        client.setAddress(clientSignupRequestModel.getStreetAddress());
	        client.setPostalZipCode(clientSignupRequestModel.getPostalZipCode());
	        client.setMobileNumber(clientSignupRequestModel.getPhoneNumber());
	        client.setUserName(clientSignupRequestModel.getUsername());
	        client.setDate(clientSignupRequestModel.getDate());
	        client.setPassword(clientSignupRequestModel.getPassword());

	        // Set city only if it exists
	        existingCity.ifPresent(client::setCity);

	        Client savedClient = clientRepo.save(client);
	        logger.info("Client successfully registered with ID: {}", savedClient.getId());
	    }

	    public void login(ClientLoginRequestModel clientLoginRequestModel) throws Exception {
	        logger.info("Login attempt for username: {}", clientLoginRequestModel.getUsername());

	        Optional<Client> clientOptional = clientRepo.findByUserNameAndPassword(
	            clientLoginRequestModel.getUsername(), clientLoginRequestModel.getPassword()
	        );

	        if (clientOptional.isEmpty()) {
	            logger.warn("Failed login attempt for username: {}", clientLoginRequestModel.getUsername());
	            throw new Exception("Wrong username or password.");
	        }

	        Client client = clientOptional.get();
	        if (Boolean.TRUE.equals(client.getIsDeleted())) {
	            logger.warn("Login attempt for deactivated user: {}", client.getUserName());
	            throw new Exception("This user account is deactivated.");
	        }

	        logger.info("Login successful for user: {}", client.getUserName());
	    }

	    public void updateClient(ClientUpdateRequestModel request) throws Exception {
	        logger.info("Updating client ID: {}", request.getId());

	        Client client = clientRepo.findById(request.getId()).orElse(null);
	        if (client == null) {
	            throw new Exception("Client not found.");
	        }

	        // Check if email is being changed and is unique
	        if (!client.getEmail().equals(request.getEmail()) && clientRepo.findByEmail(request.getEmail()).isPresent()) {
	            throw new Exception("Email is already taken.");
	        }

	        // Update client details
	        client.setEmail(request.getEmail());
	        client.setFirstName(request.getFirstName());
	        client.setLastName(request.getLastName());
	        client.setAddress(request.getAddress());
	        client.setMobileNumber(request.getPhoneNumber());
	        client.setCompanyName(request.getCompanyName());

	        // Update city if provided
	        if (request.getCityId() != null && request.getCityId() != 0) {
	            City city = cityRepo.findById(request.getCityId()).orElse(null);
	            if (city == null) {
	                throw new Exception("City not found.");
	            }
	            client.setCity(city);
	        }

	        clientRepo.save(client);
	        logger.info("Client updated successfully: {}", request.getId());
	    }
	    public void softDeleteById(int id) throws Exception {
	        logger.info("Soft delete request received for client ID: {}", id);

	        Client client = clientRepo.findById(id).orElse(null);
	        if (client == null) {
	            throw new Exception("Client does not exist.");
	        }

	        if (client.getIsDeleted()) {
	            logger.warn("Attempt to delete already deleted client ID: {}", id);
	            throw new Exception("Client already deleted.");
	        }

	        client.setIsDeleted(true);
	        clientRepo.save(client);
	        logger.info("Client soft deleted successfully for ID: {}", id);
	    }
	    
	    public void restoreById(Integer id) throws Exception {
	        Optional<Client> clientOptional = clientRepo.findById(id);
	        
	        if (!clientOptional.isPresent()) {
	            throw new Exception("Client does not exist.");
	        }

	        Client client = clientOptional.get();
	        if (!client.getIsDeleted()) { // Agar already active hai
	            throw new Exception("Client is already active.");
	        }

	        clientRepo.restoreClient(id);
	    }
	


	    public ClientResponseModel findByClientId(int clientId) throws Exception {
	        logger.info("Fetching client details for ID: {}", clientId);

	        Client client = clientRepo.findById(clientId).orElse(null);
	        if (client == null) {
	            logger.warn("Client not found for ID: {}", clientId);
	            throw new Exception("Client does not exist.");
	        }

	        return mapToResponse(client);
	    }

	    public ClientResponseModel findClientByEmail(String email) throws Exception {
	        logger.info("Fetching client by email: {}", email);

	        Client client = clientRepo.findByEmail(email).orElse(null);
	        if (client == null) {
	            throw new Exception("Client does not exist.");
	        }

	        return mapToResponse(client);
	    }

	    public ClientResponseModel findClientByPhone(String phone) throws Exception {
	        logger.info("Fetching client by phone number: {}", phone);

	        Client client = clientRepo.findByPhoneNumber(phone).orElse(null);
	        if (client == null) {
	            throw new Exception("Client does not exist.");
	        }

	        return mapToResponse(client);
	    }

	    public ClientResponseModel findClientByName(String name) throws Exception {
	        logger.info("Fetching client by name: {}", name);

	        Client client = clientRepo.findByFirstName(name).orElse(null);
	        if (client == null) {
	            throw new Exception("Client does not exist.");
	        }

	        return mapToResponse(client);
	    }

	    private ClientResponseModel mapToResponse(Client client) {
	        ClientResponseModel response = new ClientResponseModel();
	        response.setId(client.getId());
	        response.setFirstName(client.getFirstName());
	        response.setLastName(client.getLastName());
	        response.setEmailAddress(client.getEmail());
	        response.setPhoneNumber(client.getMobileNumber());
	        response.setCompanyName(client.getCompanyName());
	        response.setAddress(client.getAddress());

	        return response;
	    }


    public List<ClientResponseModel> findAll(Integer page, Integer size) throws Exception {
        logger.info("Fetching all active clients.");

        
        Page<Client> clientPage = clientRepo.findAll(PageRequest.of(page, size));

        List<ClientResponseModel> clientResponseModels = new ArrayList<>();

        for (Client client : clientPage.getContent()) {  
            if (Boolean.FALSE.equals(client.getIsDeleted())) {  
                ClientResponseModel responseModel = new ClientResponseModel();
                responseModel.setId(client.getId());
                responseModel.setFirstName(client.getFirstName());
                responseModel.setLastName(client.getLastName());
                responseModel.setEmailAddress(client.getEmail());
                responseModel.setPhoneNumber(client.getMobileNumber());
                responseModel.setCompanyName(client.getCompanyName());
                responseModel.setAddress(client.getAddress());

                clientResponseModels.add(responseModel);
            }
        }

        logger.info("Total active clients fetched: {}", clientResponseModels.size());
        return clientResponseModels;
    }

    public long countAllClient() throws Exception {
        return clientRepo.countAllClient();
    }
}

//	    public List<ClientResponseModel> findAll(Integer page , Integer size) throws Exception {
//	        logger.info("Fetching all active clients.");
//
//
//	        List<Client> clientList = (List<Client>) clientRepo.findAll(PageRequest.of(page, size));
//	        List<ClientResponseModel> clientResponseModels = new ArrayList<>();
//
//	        for (Client client : clientList) {
//	            if (!client.getIsDeleted()) {
//	                ClientResponseModel responseModel = new ClientResponseModel();
//	                responseModel.setId(client.getId());
//	                responseModel.setFirstName(client.getFirstName());
//	                responseModel.setLastName(client.getLastName());
//	                responseModel.setEmailAddress(client.getEmail());
//	                responseModel.setPhoneNumber(client.getMobileNumber());
//	                responseModel.setCompanyName(client.getCompanyName());
//	                responseModel.setAddress(client.getAddress());
//
//	                clientResponseModels.add(responseModel);
//	            }
//	        }
//
//	        logger.info("Total active clients fetched: {}", clientResponseModels.size());
//	        return clientResponseModels;
//	    }
//        public long countAllClient() throws Exception{
//        	return clientRepo.countAllClient();
//        }
//	}
//
