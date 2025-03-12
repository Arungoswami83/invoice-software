package com.amstech.invoice.service.request.model;

import lombok.Data;

@Data
public class CompanyLoginRequestModel {
	
	private String adminUsername;
	private String password;
}
