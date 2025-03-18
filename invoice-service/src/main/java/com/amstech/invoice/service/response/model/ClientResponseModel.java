package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;


@Data
public class ClientResponseModel {
	
	private int id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String companyName;
    private String address;
    private String phoneNumber;
    private String email;
    private int cityId;
    private String businessName;
    private String  postalZipCode;
    private String linkedin_profile_url;
    private String PanNumber;
    private String  specificRegistrationDetails;  
    
    
   
   
   
}
