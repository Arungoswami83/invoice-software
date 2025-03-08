package com.amstech.invoice.service.request.model;

import lombok.Data;

@Data
public class ClientUpdateRequestModel {

		private int id;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String address;
	    private String phoneNumber;
	    private String companyName;
	    private Integer cityId;
				
		

}
