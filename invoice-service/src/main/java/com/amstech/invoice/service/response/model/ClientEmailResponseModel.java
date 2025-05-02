package com.amstech.invoice.service.response.model;

import lombok.Data;

@Data
public class ClientEmailResponseModel {
	 private Integer clientId;
//	 private Integer companyId;
	 private String firstName;
	 private String lastName;
	 private String mobileNumber;
	 private String email;

}
