package com.amstech.invoice.service.converter.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Client;

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
	            responseModel.setEmailAddress(client.getEmail());
	            responseModel.setMobileNumber(client.getMobileNumber());
	            responseModel.setCompanyName(client.getCompanyName());

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
        responseModel.setEmailAddress(client.getEmail());
        responseModel.setMobileNumber(client.getMobileNumber());
        responseModel.setCompanyName(client.getCompanyName());
       
	return responseModel;

}


}
