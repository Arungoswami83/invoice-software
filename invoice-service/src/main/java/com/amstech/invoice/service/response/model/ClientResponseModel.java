package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;


@Data
public class ClientResponseModel {
	private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private String companyName;
    private String address;
}
