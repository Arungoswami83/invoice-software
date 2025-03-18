package com.amstech.invoice.service.converter.entity;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.request.model.ClientSignupRequestModel;
import com.amstech.invoice.service.request.model.ClientUpdateRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
@Component
public class ClientModelToEntityConverter {

	public Client getSaveConvert(ClientSignupRequestModel clientSignupRequestModel) {
		
		Client client = new Client();
		
        client.setFirstName(clientSignupRequestModel.getFirstName());
        client.setLastName(clientSignupRequestModel.getLastName());
        client.setEmail(clientSignupRequestModel.getEmail());
        client.setCompanyName(clientSignupRequestModel.getCompanyName());
        client.setBusinessName(clientSignupRequestModel.getBusinessName());
        client.setLinkedinProfileUrl(clientSignupRequestModel.getLinkedinProfileUrl());
        client.setBillingAddress(clientSignupRequestModel.getBillingAddress());
        client.setSpecificRegistrationDetails(clientSignupRequestModel.getSpecificRegistrationDetails());
        client.setAddress(clientSignupRequestModel.getStreetAddress());
        client.setPostalZipCode(clientSignupRequestModel.getPostalZipCode());
        client.setMobileNumber(clientSignupRequestModel.getMobileNumber());
        client.setUserName(clientSignupRequestModel.getUsername());
        client.setPassword(clientSignupRequestModel.getPassword());
        client.setPanNumber(clientSignupRequestModel.getPanNumber());
        client.setPhoneNumber(clientSignupRequestModel.getPhoneNumber());
        client.setSpecificRegistrationDetails(clientSignupRequestModel.getSpecificRegistrationDetails());
        
        return client;

	}
	public Client getUpdateConvert(ClientUpdateRequestModel clientUpdateRequestModel,Client client) {
		
		
		client.setEmail(clientUpdateRequestModel.getEmail());
        client.setFirstName(clientUpdateRequestModel.getFirstName());
        client.setLastName(clientUpdateRequestModel.getLastName());
        client.setAddress(clientUpdateRequestModel.getAddress());
        client.setMobileNumber(clientUpdateRequestModel.getMobileNumber());
        client.setCompanyName(clientUpdateRequestModel.getCompanyName());
        client.setPanNumber(clientUpdateRequestModel.getPanNumber());
        client.setPhoneNumber(clientUpdateRequestModel.getPhoneNumber());
        client.setEmail(clientUpdateRequestModel.getEmail());
       
        
        return client;
	}
}
