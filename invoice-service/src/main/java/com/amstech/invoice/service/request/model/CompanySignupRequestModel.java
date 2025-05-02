package com.amstech.invoice.service.request.model;

import lombok.Data;

@Data
public class CompanySignupRequestModel {

	    private String address;
	    private String adminUserName;  
	    private int  businessTypesId  ;
	    private String cinNo;
	    private String companyPhone;
	    private String email;
	    private String logo;
	    private String name;
	    private String password;
	    private String registrationNo;
	    private String taxIdentificationNumber;
	    private String taxPayer;
	    private String website;
	    private int clientId;
  	}

