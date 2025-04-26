	package com.amstech.invoice.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.converter.entity.ClientModelToEntityConverter;
import com.amstech.invoice.service.converter.model.ClientEntityToModelConverter;
import com.amstech.invoice.service.entity.BusinessTypes;
import com.amstech.invoice.service.entity.City;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.repo.CityRepo;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.request.model.ClientLoginRequestModel;
import com.amstech.invoice.service.request.model.ClientSignupRequestModel;
import com.amstech.invoice.service.request.model.ClientUpdateRequestModel;
import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.request.model.CompanyUpdateRequestModel;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;




@Service
public class ClientService {
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private ClientEntityToModelConverter clientEntityToModelConverter;
	@Autowired
	private ClientModelToEntityConverter clientModelToEntityConverter;
	
	private ClientResponseModel clientResponseModel;
	
	
	    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
	    


	    public ClientResponseModel signup(ClientSignupRequestModel clientSignupRequestModel) throws Exception {
	        
	        Optional<City> existingCity = cityRepo.findById(clientSignupRequestModel.getCityId());
	         
	        if (!existingCity.isPresent()) {
  	            logger.error("client does not exist for ID: {}", clientSignupRequestModel.getCityId());
   	            throw new Exception("Business Type does not exist.");
   	        }
	        Client client = clientModelToEntityConverter.getSaveConvert(clientSignupRequestModel);
	        client.setCity(existingCity.get());
	        Client savedClient = clientRepo.save(client);
	        //ClientResponseModel clientResponseModel = new ClientResponseModel();
	        return clientEntityToModelConverter.getfindById(savedClient);
	    }
	    
	    public ClientResponseModel login(ClientLoginRequestModel clientLoginRequestModel) throws Exception {
	       
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

	        return clientEntityToModelConverter.getfindById(client);
	    }
	    
	    public ClientResponseModel updateClient(ClientUpdateRequestModel clientUpdateRequestModel) throws Exception {
    
	    	Optional<Client> clientOptional = clientRepo.findById(clientUpdateRequestModel.getId());
	 
	    	if (clientOptional.isEmpty()) {
	    		throw new Exception("The client account does not exist.");
	    	}
	    	Client client = clientOptional.get();
	    	try {
	    		if (!client.getEmail().equals(clientUpdateRequestModel.getEmail()) &&
	    				clientRepo.findByEmail(clientUpdateRequestModel.getEmail()).isPresent()) {
	    			throw new Exception("Email is already taken.");
	    		}
	    	} catch (Exception e) {
	    		throw new Exception("Error checking email.");
    }

    client.setEmail(clientUpdateRequestModel.getEmail());
    client.setCompanyName(clientUpdateRequestModel.getCompanyName());
    
    client = clientModelToEntityConverter.getUpdateConvert(clientUpdateRequestModel,client);
    Client savedClient = clientRepo.save(client);
    
    return clientEntityToModelConverter.getfindById(savedClient);
}

	    public void softDeleteById(int id) throws Exception {
	        
	        Optional<Client> clientOptional = clientRepo.findById(id);
	       
	        if (clientOptional == null) {
	            throw new Exception("Client does not exist.");
	        }
	        Client client = clientOptional.get();

	        if (client.getIsDeleted() == true) {
	         throw new Exception("Client already deleted.");
	        }

	        client.setIsDeleted(true);
	        client.setUpdatedAt(Timestamp.from(Instant.now()));

	        clientRepo.save(client);
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
	    
	    
	    public ClientResponseModel findByClientId(int id) throws Exception {
	    	Optional<Client> clientOptional = clientRepo.findById(id);
	      
	        if (clientOptional == null) {
	            throw new Exception("Client does not exist.");
	        }
	        Client client = clientOptional.get();
	        
	        if (client.getIsDeleted()== true) {
	          throw new Exception("Your account has been deactivated. Please contact the administrator for assistance.");
	      }
	        return clientEntityToModelConverter.getfindById(client);
	    }
	
    public List<ClientResponseModel> findAllClient(Integer page, Integer size) throws Exception {
       
        List<Client> clientList = clientRepo.findAllClient(PageRequest.of(page, size));
        return clientEntityToModelConverter.getFindAllConvert(clientList);
    }

    public long countAllClient() throws Exception {
        return clientRepo.countAllClient();
    }
}

	    
	    
	    
	    

