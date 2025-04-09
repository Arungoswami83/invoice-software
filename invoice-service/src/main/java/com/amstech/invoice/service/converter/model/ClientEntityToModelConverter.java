package com.amstech.invoice.service.converter.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.response.model.ClientEmailResponseModel;
import com.amstech.invoice.service.response.model.ClientResponseModel;


@Component
public class ClientEntityToModelConverter {
	
	public List<ClientResponseModel> getFindAllConvert(List<Client> clientList){
		 List<ClientResponseModel> clientResponseModels = new ArrayList<>();

	        for (Client client : clientList) {
	            ClientResponseModel responseModel = new ClientResponseModel();
	            responseModel.setId(client.getId());
	            responseModel.setFirstName(client.getFirstName());
	            responseModel.setLastName(client.getLastName());

	            responseModel.setAddress(client.getAddress());
	            responseModel.setMobileNumber(client.getMobileNumber());
	            responseModel.setCompanyName(client.getCompanyName());
	           responseModel.setPhoneNumber(client.getPhoneNumber());
	           responseModel.setPanNumber(client.getPanNumber());
	           responseModel.setBusinessName(client.getBusinessName());
	           responseModel.setPostalZipCode(client.getPostalZipCode());
	           responseModel.setLinkedin_profile_url(client.getLinkedinProfileUrl());
	           responseModel.setCityId(client.getCity().getId());
	           responseModel.setSpecificRegistrationDetails(client.getSpecificRegistrationDetails());
	           responseModel.setEmail(client.getEmail());

	            clientResponseModels.add(responseModel);
	        }
	        return clientResponseModels;

	}
	public ClientResponseModel getfindById(Client client){
		
		
		ClientResponseModel responseModel = new ClientResponseModel();

		responseModel.setId(client.getId());
        responseModel.setFirstName(client.getFirstName());
        responseModel.setLastName(client.getLastName());

        responseModel.setAddress(client.getAddress());
        responseModel.setMobileNumber(client.getMobileNumber());
        responseModel.setCompanyName(client.getCompanyName());
        responseModel.setPhoneNumber(client.getPhoneNumber());
        responseModel.setPanNumber(client.getPanNumber());
        responseModel.setBusinessName(client.getBusinessName());
        responseModel.setPostalZipCode(client.getPostalZipCode());
        responseModel.setLinkedin_profile_url(client.getLinkedinProfileUrl());
        responseModel.setCityId(client.getCity().getId());
        responseModel.setEmail(client.getEmail());
       responseModel.setSpecificRegistrationDetails(client.getSpecificRegistrationDetails());

	return responseModel;


}
//	public ClientEmailResponseModel getDetailsUser(Client client) {
//		ClientEmailResponseModel emailResponseModel=new ClientEmailResponseModel();
//		
//		emailResponseModel.setClientId(client.getId());
//		emailResponseModel.setCompanyId(client.getCompanies().get());
//		
//	}


}
