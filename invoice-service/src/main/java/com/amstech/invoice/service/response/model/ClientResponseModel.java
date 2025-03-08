package com.amstech.invoice.service.response.model;

import com.amstech.invoice.service.entity.Client;

import lombok.Data;
@Data
public class ClientResponseModel {
	private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String companyName;
    private String address;
    private Boolean isDeleted;
}
