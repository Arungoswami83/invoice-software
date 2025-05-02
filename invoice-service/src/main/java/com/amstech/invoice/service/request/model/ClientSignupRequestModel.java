package com.amstech.invoice.service.request.model;

import java.sql.Date;

import com.amstech.invoice.service.entity.City;

import lombok.Data;

@Data
public class ClientSignupRequestModel {

	
		
	    private String companyName;
	    private String businessName;
	    private String email;
	    private String linkedinProfileUrl;
	    private String billingAddress;
	    private String gender;
	    private String specificRegistrationDetails;
	    private Date registrationDate;
	    private String firstName;
	    private String lastName;
	    private String streetAddress;
	    private String streetAddressLine2;
	    private int cityId;
	    private String stateProvince;
	    private String postalZipCode;
	    private String mobileNumber;
	    private String username;
	    private String password;
	    private Date date;
	    private String phoneNumber;
	    private String panNumber;
	    private int quantity; 
	    
	    
		
	    
	}


