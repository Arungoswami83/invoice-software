package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private Integer cityId;
    private String businessName;
    private String  postalZipCode;
    private String linkedin_profile_url;
    private String PanNumber;
    private String  specificRegistrationDetails;  
    private String gender;
    private LocalDateTime createdAt;
    private Date dueDate;
    private String status;
    private BigDecimal tax;
    private int quantity;
    private String productCode;
        
    }

  