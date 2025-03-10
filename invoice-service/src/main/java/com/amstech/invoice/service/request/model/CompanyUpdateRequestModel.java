package com.amstech.invoice.service.request.model;

import java.util.Date;

import lombok.Data;

@Data
public class CompanyUpdateRequestModel {
	
	private int id;
	private String name;
	private String address;
    private int businessTypesId;
    private String companyPhone;
    private String email;
    private String logo;
    private String password;
    private String taxPayer;
    private String website;
    private boolean isEmailUpdate;
    private boolean isEmailVerified;
    private String adminUserName;
    private Date updated_At;
    private int clientId;
   
}
